package com.example.devlist.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.ExtensionFragmentBinding
import com.example.devlist.ui.adapters.ExtensionAdapter
import com.example.devlist.ui.viewmodel.ExtensionViewModel

class ExtensionFragment : Fragment() {
    private var _binding: ExtensionFragmentBinding? = null
    private val binding get() = _binding!!
    private val extensionViewModel: ExtensionViewModel by viewModels()
    lateinit var adapter: ExtensionAdapter
    private var extensionArticles = mutableListOf<Resource>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ExtensionFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            extensionRecycler.apply {
                extensionRecycler.layoutManager = LinearLayoutManager(activity)
            }

            extensionViewModel.extensionLiveData.observe(this@ExtensionFragment.viewLifecycleOwner) {
                extensionProgressBar.visibility = View.GONE
                if (it != null) {
                    extensionRecycler.visibility = View.VISIBLE
                    extensionArticles = it.resources as MutableList<Resource>
                    adapter = ExtensionAdapter(requireActivity(),extensionArticles)
                    extensionRecycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}