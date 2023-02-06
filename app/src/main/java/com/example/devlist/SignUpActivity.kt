package com.example.devlist

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.devlist.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.public_api_fragment.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating An Account")
        progressDialog.setCanceledOnTouchOutside(false)

        auth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener{
            validateData()
        }

    }

    private fun validateData() {
        var email = binding.emailEdt.text.toString().trim()
        var password = binding.passwordEdt.text.toString().trim()
        var confPass = binding.confPass.text.toString().trim()
        var username = binding.usernameEdt.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEdt.setError("Inavlid Email Format")
            binding.emailEdt.requestFocus()
        }
        else if(username.isEmpty()){
            binding.usernameEdt.error="Username required!!!"
            binding.usernameEdt.requestFocus()
        }
        else if(binding.passwordEdt.text.toString().isEmpty()){
            binding.passwordEdt.error="Password can not be empty!"
            binding.passwordEdt.requestFocus()
        }
        else if(binding.passwordEdt.text.toString().length<6){
            binding.passwordEdt.error="Password length should be at least 6!"
            binding.passwordEdt.requestFocus()
        }
        else if(binding.confPass.text.toString()!=binding.passwordEdt.text.toString()){
            binding.confPass.error="Password mismatched!"
            binding.confPass.requestFocus()
        }
        else{
            firebaseSignUp()
        }

    }

    private fun firebaseSignUp() {
        progressDialog.show()
        auth.createUserWithEmailAndPassword(binding.emailEdt.text.toString(),binding.passwordEdt.text.toString()).addOnSuccessListener {
            progressDialog.dismiss()

            val name = binding.usernameEdt.text.toString()
            val email = binding.emailEdt.text.toString()
            val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
            val database = FirebaseDatabase.getInstance().reference
            val data = HashMap<String, Any>()
            data["Name"] = name
            data["email"] = email
            data["user_UID"] = uid
            database.child("users").child(uid).setValue(data)

            Toast.makeText(this,"Account created successfully ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this, "Sign Up FAILED DUE TO $e", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, SplashScreen::class.java))
        moveTaskToBack(true)
    }
}