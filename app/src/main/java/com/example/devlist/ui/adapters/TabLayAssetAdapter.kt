package com.example.devlist.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.devlist.ui.fragments.FontFragment
import com.example.devlist.ui.fragments.IconsFragment
import com.example.devlist.ui.fragments.ImageFragment
import com.example.devlist.ui.fragments.LogoFragment

class TabLayAssetAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FontFragment()
            }
            1 -> {
                ImageFragment()
            }
            2 -> {
                LogoFragment()
            }
            3 -> {
                IconsFragment()
            }
            else -> {
                FontFragment()
            }
        }
    }
}