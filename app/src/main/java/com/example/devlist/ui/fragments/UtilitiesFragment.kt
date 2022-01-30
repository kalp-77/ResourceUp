package com.example.devlist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.devlist.R
import com.example.devlist.databinding.UtilitiesFragmentBinding
import com.example.devlist.ui.adapters.TabLayUtilitiesAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UtilitiesFragment : Fragment() {
    var tabTitle= arrayOf("Website","Extensions")
    private var _binding: UtilitiesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UtilitiesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.view_pager_2)

        viewPager2.adapter= TabLayUtilitiesAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager2){
                tab, position->
            tab.text= tabTitle[position]
        }.attach()

//        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_font_download_24)
//        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_image_24)
//        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_motion_photos_on_24)
//        tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_baseline_insert_emoticon_24)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}