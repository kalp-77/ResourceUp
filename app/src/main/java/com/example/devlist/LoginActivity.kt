package com.example.devlist

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.devlist.databinding.ActivityLogin3Binding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogin3Binding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth



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

        binding.noAccount.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.loginBtn.setOnClickListener{
            validateData()
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