package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.devlist.R
import com.example.devlist.databinding.AssetsFragmentBinding
import com.example.devlist.ui.adapters.TabLayAssetAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.assets_fragment.*

class AssetsFragment : Fragment() {

    var tabTitle= arrayOf("Fonts","Images","Logos","Icons")
    private var _binding: AssetsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = AssetsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // navController object
        val navController = Navigation.findNavController(view)

        val tabLayout=view.findViewById<TabLayout>(R.id.tab_layout)
        var viewPager2 = view.findViewById<ViewPager2>(R.id.view_pager_2)

        viewPager2.adapter= TabLayAssetAdapter(parentFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager2){
                tab, position->
            tab.text= tabTitle[position]
        }.attach()

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_font_download_24)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_image_24)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_motion_photos_on_24)
        tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_baseline_insert_emoticon_24)

//        iconBtn.setOnClickListener {
//            navController.navigate(R.id.action_assetsFragment_to_iconsFragment2)
//        }
//        fontBtn.setOnClickListener {
//            navController.navigate(R.id.action_assetsFragment_to_fontFragment)
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}