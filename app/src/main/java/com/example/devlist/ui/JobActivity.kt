package com.example.devlist.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.devlist.R
import com.example.devlist.ui.fragments.BoardsFragment
import com.example.devlist.ui.fragments.InterviewFragment
import com.example.devlist.ui.fragments.ResumeFragment
import com.example.devlist.ui.utils.applyWindowInsets
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_job.*

class JobActivity : AppCompatActivity() {

    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu3) }
    private val button by lazy { findViewById<ImageView>(R.id.expand_button3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        loadFragment(ResumeFragment.newInstance())
        menu.setOnItemSelectedListener(object : ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                when (id) {
                    R.id.resume -> {
                        switchFragment(ResumeFragment.newInstance())
                    }
                    R.id.interview -> {
                        switchFragment(InterviewFragment.newInstance())
                    }
                    R.id.boards -> {
                        switchFragment(BoardsFragment.newInstance())
                    }
                }
            }
        })
        button.setOnClickListener {
            if (menu.isExpanded()) {
                TransitionManager.beginDelayedTransition(fragmentContainerView3, ChangeBounds())
                menu.collapse()
            } else {
                TransitionManager.beginDelayedTransition(fragmentContainerView3, ChangeBounds())
                menu.expand()
            }
        }
        button.applyWindowInsets(bottom = true)
    }
        private fun switchFragment(fragment: Fragment) {
            val fragmentTransaction1 = supportFragmentManager.beginTransaction()
            fragmentTransaction1.replace(R.id.fragmentContainerView3, fragment)
            fragmentTransaction1.commit()
        }
        private fun loadFragment(fragment: Fragment) {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragmentContainerView3, fragment)
            transaction.commit()
        }
}