package com.example.devlist

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.devlist.databinding.ActivityLogin3Binding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.public_api_fragment.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogin3Binding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object{
        const val RC_SIGN_IN = 1001
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkuser()

        binding.noAccountText.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.loginBtn.setOnClickListener{
            validateData()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.googleSignInBtn.setOnClickListener{
            googleSignIn()
        }
    }

    private fun googleSignIn(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, SignUpActivity.RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SignUpActivity.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.e("TAG", "onActivityResult: Google sign in failed due to", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val name = user?.displayName.toString()
                    val email = user?.email.toString()
                    val uid = user?.uid.toString()
                    val database = FirebaseDatabase.getInstance().reference
                    val data = HashMap<String, Any>()
                    data["Name"] = name
                    data["email"] = email
                    data["user_UID"] = uid
                    database.child("users").child(uid).setValue(data)

                    Toast.makeText(this,"Logged in successfully ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun validateData() {
        val name = binding.emailEdt.text.toString().trim()
        val pass = binding.passEdt.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            binding.emailEdt.error = "Invalid Email Format"
            binding.emailEdt.requestFocus()
        }
        else if (name.isEmpty()) {
            binding.emailEdt.error = "Email Required!!!"
            binding.emailEdt.requestFocus()
        } else if (pass.isEmpty()) {
            binding.passEdt.error = "Password Required!!!"
            binding.passEdt.requestFocus()
        } else {
            firebaselogin()
        }
    }

    private fun firebaselogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(
            binding.emailEdt.text.toString(),
            binding.passEdt.text.toString()
        )
            .addOnSuccessListener {
                progressDialog.dismiss()
                val intent = (Intent(this@LoginActivity, MainActivity::class.java))
                startActivity(intent)
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Log In Failed Due To${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkuser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            val intent = (Intent(this@LoginActivity, MainActivity::class.java))
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, SplashScreen::class.java))
        moveTaskToBack(true)
    }
}