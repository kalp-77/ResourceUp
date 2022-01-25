package com.example.devlist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.devlist.R
import com.example.devlist.databinding.AssetFragmentBinding
import kotlinx.android.synthetic.main.asset_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*


class AssetFragment : Fragment() {
    private var _binding: AssetFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AssetFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // navController object
        val navController = Navigation.findNavController(view)

        fontBtn.setOnClickListener {
            navController.navigate(R.id.action_assetFragment_to_fontFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}