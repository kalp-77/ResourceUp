package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.ExtensionFragmentBinding
import com.example.devlist.ui.adapters.ExtensionAdapter
import com.example.devlist.ui.adapters.FontAdapter
import com.example.devlist.ui.viewmodel.ExtensionViewModel

class ExtensionFragment : Fragment() {

    private var _binding: ExtensionFragmentBinding? = null
    private val binding get() = _binding!!
    private val extensionViewModel: ExtensionViewModel by viewModels()
    lateinit var adapter: ExtensionAdapter
    private var extensionArticles = mutableListOf<Resource>()
    private var extensionArticles2 = mutableListOf<Resource>()

    companion object{
        fun newInstance() = ExtensionFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

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
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                extensionRecycler.visibility = View.VISIBLE
                                extensionArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.resources){
                                    if(article.name.lowercase().contains(search)){
                                        extensionArticles2.add(article)
                                    }
                                }
                                adapter = ExtensionAdapter(requireActivity(), extensionArticles2)
                                extensionRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                extensionRecycler.visibility = View.VISIBLE
                                extensionArticles2.clear()
                                extensionArticles = it.resources as MutableList<Resource>
                                adapter = ExtensionAdapter(requireActivity(), extensionArticles)
                                extensionRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
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