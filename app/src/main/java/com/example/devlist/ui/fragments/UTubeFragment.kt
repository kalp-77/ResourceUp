package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.UTubeFragmentBinding
import com.example.devlist.ui.adapters.ResumeAdapter
import com.example.devlist.ui.adapters.UTubeAdapter
import com.example.devlist.ui.viewmodel.UTubeViewModel

class UTubeFragment : Fragment() {

    private var _binding: UTubeFragmentBinding? = null
    private val binding get() = _binding!!
    private val uTubeViewModel: UTubeViewModel by viewModels()
    lateinit var adapter: UTubeAdapter
    private var uTubeArticles = mutableListOf<Resource>()
    private var uTubeArticles2 = mutableListOf<Resource>()

    companion object{
        fun newInstance() = UTubeFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UTubeFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            uTubeRecycler.apply {
                uTubeRecycler.layoutManager = LinearLayoutManager(activity)
            }
            uTubeViewModel.uTubeLiveData.observe(this@UTubeFragment.viewLifecycleOwner) {
                uTubeProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                uTubeRecycler.visibility = View.VISIBLE
                                uTubeArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.data){
                                    if(article.name.lowercase().contains(search)){
                                        uTubeArticles2.add(article)
                                    }
                                }
                                adapter = UTubeAdapter(requireActivity(), uTubeArticles2)
                                uTubeRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                uTubeRecycler.visibility = View.VISIBLE
                                uTubeArticles2.clear()
                                uTubeArticles = it.data as MutableList<Resource>
                                adapter = UTubeAdapter(requireActivity(), uTubeArticles)
                                uTubeRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    uTubeRecycler.visibility = View.VISIBLE
                    uTubeArticles = it.data as MutableList<Resource>
                    adapter = UTubeAdapter(requireActivity(), uTubeArticles)
                    uTubeRecycler.adapter = adapter
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