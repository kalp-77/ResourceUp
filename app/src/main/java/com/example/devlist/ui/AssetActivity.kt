package com.example.devlist.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.devlist.R
import com.example.devlist.ui.fragments.*
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_learn.*

class AssetActivity : AppCompatActivity() {
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }
    private val button by lazy { findViewById<ImageView>(R.id.expand_button)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset)

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        loadFragment(FontFragment.newInstance())
        menu.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                when (id) {
                    R.id.fonts -> {
                        switchFragment(FontFragment.newInstance())
                    }
                    R.id.images -> {
                        switchFragment(ImageFragment.newInstance())
                    }
                    R.id.logos -> {
                        switchFragment(LogoFragment.newInstance())
                    }
                    R.id.icons -> {
                        switchFragment(IconsFragment.newInstance())
                    }
                }
            }
        })
    }
    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction1 = supportFragmentManager.beginTransaction()
        fragmentTransaction1.replace(R.id.fragmentContainerView2, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView2, fragment)
        transaction.commit()
    }
}