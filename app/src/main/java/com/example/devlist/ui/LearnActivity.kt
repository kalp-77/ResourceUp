package com.example.devlist.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.devlist.R
import com.example.devlist.ui.fragments.CpChallengeFragment
import com.example.devlist.ui.fragments.ProgrammingFragment
import com.example.devlist.ui.fragments.UTubeFragment
import com.example.devlist.ui.fragments.UiKitFragment
import com.example.devlist.ui.utils.applyWindowInsets
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_learn.*


class LearnActivity : AppCompatActivity() {
    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }
    private val button by lazy { findViewById<ImageView>(R.id.expand_button)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        loadFragment(ProgrammingFragment.newInstance())
        menu.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                val option = when (id) {
                    R.id.learn -> {
                        switchFragment(ProgrammingFragment.newInstance())
                    }
                    R.id.uTube -> {
                        switchFragment(UTubeFragment.newInstance())
                    }
                    R.id.uiKit -> {
                        switchFragment(UiKitFragment.newInstance())
                    }
                    R.id.cp -> {
                        switchFragment(CpChallengeFragment.newInstance())
                    }
                    else -> {}
                }
            }
        })
        button.setOnClickListener {
            if (menu.isExpanded()) {
                TransitionManager.beginDelayedTransition(fragmentContainerView2, ChangeBounds())
                menu.collapse()
            } else {
                TransitionManager.beginDelayedTransition(fragmentContainerView2, ChangeBounds())
                menu.expand()
            }
        }
        button.applyWindowInsets(bottom = true)

    }
    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction1 = supportFragmentManager.beginTransaction()
        fragmentTransaction1.replace(R.id.fragmentContainerView2, fragment)
        fragmentTransaction1.commit()
    }
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        //frame_container is your layout name in xml file
        transaction.add(R.id.fragmentContainerView2, fragment)
        transaction.commit()
    }

}