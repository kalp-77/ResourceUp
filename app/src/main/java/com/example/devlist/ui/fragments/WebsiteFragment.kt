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
import com.example.devlist.databinding.WebsiteFragmentBinding
import com.example.devlist.ui.adapters.WebsiteAdapter
import com.example.devlist.ui.viewmodel.WebsiteViewModel
import kotlinx.android.synthetic.main.website_fragment.*

class WebsiteFragment : Fragment() {
    private var _binding: WebsiteFragmentBinding? = null
    private val binding get() = _binding!!
    private val websiteViewModel: WebsiteViewModel by viewModels()
    lateinit var adapter: WebsiteAdapter
    private var websiteArticles = mutableListOf<Resource>()

    companion object{
        fun newInstance() = WebsiteFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WebsiteFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            websiteRecycler.apply {
                websiteRecycler.layoutManager = LinearLayoutManager(activity)
            }

            websiteViewModel.websiteLiveData.observe(this@WebsiteFragment.viewLifecycleOwner) {
                websiteProgressBar.visibility = View.GONE
                if (it != null) {
                    websiteRecycler.visibility = View.VISIBLE
                    websiteArticles = it.resources as MutableList<Resource>
                    adapter = WebsiteAdapter(requireActivity(), websiteArticles)
                    websiteRecycler.adapter = adapter
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