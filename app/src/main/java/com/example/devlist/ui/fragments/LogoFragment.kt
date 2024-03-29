package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.LogoFragmentBinding
import com.example.devlist.ui.adapters.IconAdapter
import com.example.devlist.ui.adapters.LogoAdapter
import com.example.devlist.ui.viewmodel.LogoViewModel

class LogoFragment : Fragment() {

    private var _binding: LogoFragmentBinding? = null
    private val binding get() = _binding!!
    private val logoViewModel: LogoViewModel by viewModels()
    lateinit var adapter: LogoAdapter
    private var logoArticles = mutableListOf<Resource>()
    private var logoArticles2 = mutableListOf<Resource>()


    companion object{
        fun newInstance() = LogoFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LogoFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            logoRecycler.apply {
                logoRecycler.layoutManager = LinearLayoutManager(activity)
            }
            logoViewModel.logoLiveData.observe(this@LogoFragment.viewLifecycleOwner) {
                logoProgressBar.visibility = View.GONE
                if (it != null) {
                    logoRecycler.visibility = View.VISIBLE
                    logoArticles.addAll(it.data)
                }
            }
            logoViewModel.logoLiveData2.observe(this@LogoFragment.viewLifecycleOwner) {
                if (it != null) {
                    logoArticles.addAll(it.data)
                }
            }
            searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    if(p0!!.isNotEmpty()){
                        logoRecycler.visibility = View.VISIBLE
                        logoArticles2.clear()
                        val search = p0.lowercase()
                        for(article in logoArticles){
                            if(article.name.lowercase().contains(search)){
                                logoArticles2.add(article)
                            }
                        }
                        adapter = LogoAdapter(requireActivity(), logoArticles2)
                        logoRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    else{
                        logoRecycler.visibility = View.VISIBLE
                        logoArticles2.clear()
                        adapter = LogoAdapter(requireActivity(), logoArticles)
                        logoRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
            adapter = LogoAdapter(requireActivity(), logoArticles)
            logoRecycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}