package com.example.devlist.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.devlist.R
import com.example.devlist.databinding.HomeFragmentBinding
import com.example.devlist.databinding.HomeFragmentBinding.inflate
import com.example.devlist.ui.LearnActivity
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    // binding HomeFragment
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // navController object
        val navController = Navigation.findNavController(view)

        apiBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_publicApiFragment)
        }
        uiBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_uiFragment)
        }
        assetsBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_assetsFragment)
        }
        learnBtn.setOnClickListener {
            val intent = Intent(context, LearnActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}