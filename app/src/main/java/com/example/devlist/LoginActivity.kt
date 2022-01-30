package com.example.devlist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login3.*

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var isRemembered= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login3)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        isRemembered= sharedPreferences.getBoolean("CHECKBOX", false)
        if(isRemembered){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        save.setOnClickListener{
            val name: String = nameEdt.text.toString()
            val checked: Boolean = checkBox.isChecked

            val editor: SharedPreferences.Editor= sharedPreferences.edit()
            editor.putString("NAME", name)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()

            Toast.makeText(this,"Saved", Toast.LENGTH_LONG).show()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}