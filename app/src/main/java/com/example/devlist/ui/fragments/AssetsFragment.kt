package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.devlist.R
import com.example.devlist.databinding.AssetsFragmentBinding
import kotlinx.android.synthetic.main.assets_fragment.*

class AssetsFragment : Fragment() {

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

        iconBtn.setOnClickListener {
            navController.navigate(R.id.action_assetsFragment_to_iconsFragment2)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}