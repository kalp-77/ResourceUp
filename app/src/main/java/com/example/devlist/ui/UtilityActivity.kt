package com.example.devlist.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.devlist.R
import com.example.devlist.ui.fragments.ExtensionFragment
import com.example.devlist.ui.fragments.WebsiteFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class UtilityActivity : AppCompatActivity() {
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utility)

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        loadFragment(WebsiteFragment.newInstance())
        menu.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                when (id) {
                    R.id.website -> {
                        switchFragment(WebsiteFragment.newInstance())
                    }
                    R.id.extension -> {
                        switchFragment(ExtensionFragment.newInstance())
                    }
                }
            }
        })
    }
    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction1 = supportFragmentManager.beginTransaction()
        fragmentTransaction1.replace(R.id.fragmentContainerView2, fragment)
        fragmentTransaction1.commit()
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView2, fragment)
        transaction.commit()
    }
}