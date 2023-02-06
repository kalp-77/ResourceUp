package com.example.devlist

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.devlist.databinding.ActivityLogin3Binding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login3.*
import kotlinx.coroutines.DelicateCoroutinesApi

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogin3Binding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth

//    lateinit var sharedPreferences: SharedPreferences
//    private var isRemembered = false

    @OptIn(DelicateCoroutinesApi::class)
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
//        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
//        isRemembered= sharedPreferences.getBoolean("CHECKBOX", false)
//        if(isRemembered){
//            val intent= Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        binding.noAccount.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.loginBtn.setOnClickListener{
            validateData()
//            val name: String = nameEdt.text.toString()
//            val checked: Boolean = checkBox.isChecked
//
//            val editor: SharedPreferences.Editor= sharedPreferences.edit()
//            editor.putString("NAME", name)
//            editor.putBoolean("CHECKBOX", checked)
//            editor.apply()
//
//            Toast.makeText(this,"Saved", Toast.LENGTH_LONG).show()
//            val intent= Intent(this,MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }

    private fun validateData() {
        var name = binding.emailEdt.text.toString().trim()
        var pass = binding.passEdt.text.toString().trim()
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
//                val firebaseUser = firebaseAuth.currentUser
//                val email = firebaseUser!!.email
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