package com.example.devlist.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.R
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.FontFragmentBinding
import com.example.devlist.databinding.ImageFragmentBinding
import com.example.devlist.ui.adapters.FontAdapter
import com.example.devlist.ui.adapters.ImageAdapter
import com.example.devlist.ui.viewmodel.FontViewModel
import com.example.devlist.ui.viewmodel.ImageViewModel

class ImageFragment : Fragment() {

    private var _binding: ImageFragmentBinding? = null
    private val binding get() = _binding!!
    private val imageViewModel: ImageViewModel by viewModels()

    lateinit var adapter: ImageAdapter
    private var imageArticles = mutableListOf<Resource>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ImageFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            imageRecycler.apply {
                imageRecycler.layoutManager = LinearLayoutManager(activity)
            }

            imageViewModel.imageLiveData.observe(this@ImageFragment.viewLifecycleOwner, {
                imageProgressBar.visibility = View.GONE
                if (it != null) {
                    imageRecycler.visibility = View.VISIBLE
                    imageArticles = it.resources as MutableList<Resource>
                    adapter = ImageAdapter(requireActivity(), imageArticles)
                    imageRecycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            })
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}