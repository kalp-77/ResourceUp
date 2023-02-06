package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.FontFragmentBinding
import com.example.devlist.ui.adapters.FontAdapter
import com.example.devlist.ui.viewmodel.FontViewModel

class FontFragment : Fragment() {

    private var _binding: FontFragmentBinding? = null
    private val binding get() = _binding!!
    private val fontViewModel: FontViewModel by viewModels()
    lateinit var adapter: FontAdapter
    private var fontArticles = mutableListOf<Resource>()
    private var fontArticles2 = mutableListOf<Resource>()  // contains searched items

    companion object{
        fun newInstance() = FontFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FontFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            fontRecycler.apply {
                fontRecycler.layoutManager = LinearLayoutManager(activity)
            }
            fontViewModel.fontLiveData.observe(this@FontFragment.viewLifecycleOwner) {
                fontProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                fontRecycler.visibility = View.VISIBLE
                                fontArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.data){
                                    if(article.name.lowercase().contains(search)){
                                        fontArticles2.add(article)
                                    }
                                }
                                adapter = FontAdapter(requireActivity(), fontArticles2)
                                fontRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                fontRecycler.visibility = View.VISIBLE
                                fontArticles2.clear()
                                fontArticles = it.data as MutableList<Resource>
                                adapter = FontAdapter(requireActivity(), fontArticles)
                                fontRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    fontRecycler.visibility = View.VISIBLE
                    fontArticles = it.data as MutableList<Resource>
                    adapter = FontAdapter(requireActivity(), fontArticles)
                    fontRecycler.adapter = adapter
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