package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.ResumeFragmentBinding
import com.example.devlist.ui.adapters.IconAdapter
import com.example.devlist.ui.adapters.ResumeAdapter
import com.example.devlist.ui.viewmodel.ResumeViewModel

class ResumeFragment : Fragment() {
    private var _binding: ResumeFragmentBinding? = null
    private val binding get() = _binding!!
    private val resumeViewModel: ResumeViewModel by viewModels()
    lateinit var adapter: ResumeAdapter
    private var resumeArticles = mutableListOf<Resource>()
    private var resumeArticles2 = mutableListOf<Resource>()


    companion object{
        fun newInstance() = ResumeFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResumeFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            resumeRecycler.apply {
                resumeRecycler.layoutManager = LinearLayoutManager(activity)
            }
            resumeViewModel.resumeLiveData.observe(this@ResumeFragment.viewLifecycleOwner) {
                resumeProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                resumeRecycler.visibility = View.VISIBLE
                                resumeArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.data){
                                    if(article.name.lowercase().contains(search)){
                                        resumeArticles2.add(article)
                                    }
                                }
                                adapter = ResumeAdapter(requireActivity(), resumeArticles2)
                                resumeRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                resumeRecycler.visibility = View.VISIBLE
                                resumeArticles2.clear()
                                resumeArticles = it.data as MutableList<Resource>
                                adapter = ResumeAdapter(requireActivity(), resumeArticles)
                                resumeRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    resumeRecycler.visibility = View.VISIBLE
                    resumeArticles = it.data as MutableList<Resource>
                    adapter = ResumeAdapter(requireActivity(), resumeArticles)
                    resumeRecycler.adapter = adapter
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