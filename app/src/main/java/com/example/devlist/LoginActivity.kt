package com.example.devlist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_login3.*
import kotlin.system.exitProcess
import kotlinx.coroutines.DelicateCoroutinesApi

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    private var isRemembered = false

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login3)



        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

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


    override fun onBackPressed() {
        startActivity(Intent(this, SplashScreen::class.java))
        moveTaskToBack(true)
    }
}