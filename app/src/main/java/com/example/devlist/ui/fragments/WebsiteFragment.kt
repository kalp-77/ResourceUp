package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.WebsiteFragmentBinding
import com.example.devlist.ui.adapters.ResumeAdapter
import com.example.devlist.ui.adapters.WebsiteAdapter
import com.example.devlist.ui.viewmodel.WebsiteViewModel

class WebsiteFragment : Fragment() {

    private var _binding: WebsiteFragmentBinding? = null
    private val binding get() = _binding!!
    private val websiteViewModel: WebsiteViewModel by viewModels()
    lateinit var adapter: WebsiteAdapter
    private var websiteArticles = mutableListOf<Resource>()
    private var websiteArticles2 = mutableListOf<Resource>()

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
                    websiteArticles.addAll(it.resources)
                }
            }
            websiteViewModel.website2LiveData.observe(this@WebsiteFragment.viewLifecycleOwner) {
                websiteProgressBar.visibility = View.GONE
                if (it != null) {
                    websiteArticles.addAll(it.resources)
                }
            }
            searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    if(p0!!.isNotEmpty()){
                        websiteRecycler.visibility = View.VISIBLE
                        websiteArticles2.clear()
                        val search = p0.lowercase()
                        for(article in websiteArticles){
                            if(article.name.lowercase().contains(search)){
                                websiteArticles2.add(article)
                            }
                        }
                        adapter = WebsiteAdapter(requireActivity(), websiteArticles2)
                        websiteRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    else{
                        websiteRecycler.visibility = View.VISIBLE
                        websiteArticles2.clear()
                        adapter = WebsiteAdapter(requireActivity(), websiteArticles)
                        websiteRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
            adapter = WebsiteAdapter(requireActivity(), websiteArticles)
            websiteRecycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}