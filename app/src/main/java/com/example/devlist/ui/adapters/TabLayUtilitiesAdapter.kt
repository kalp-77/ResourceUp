package com.example.devlist.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.devlist.ui.fragments.ExtensionFragment
import com.example.devlist.ui.fragments.WebsiteFragment

class TabLayUtilitiesAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                WebsiteFragment()
            }
            1 -> {
                ExtensionFragment()
            }
            else -> {
                WebsiteFragment()
            }
        }
    }


}