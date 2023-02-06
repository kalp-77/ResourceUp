package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.ImageFragmentBinding
import com.example.devlist.ui.adapters.IconAdapter
import com.example.devlist.ui.adapters.ImageAdapter
import com.example.devlist.ui.viewmodel.ImageViewModel

class ImageFragment : Fragment() {

    private var _binding: ImageFragmentBinding? = null
    private val binding get() = _binding!!
    private val imageViewModel: ImageViewModel by viewModels()
    lateinit var adapter: ImageAdapter
    private var imageArticles = mutableListOf<Resource>()
    private var imageArticles2 = mutableListOf<Resource>()


    companion object{
        fun newInstance() = ImageFragment().apply {
            arguments = Bundle().apply {  }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ImageFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            imageRecycler.apply {
                imageRecycler.layoutManager = LinearLayoutManager(activity)
            }

            imageViewModel.imageLiveData.observe(this@ImageFragment.viewLifecycleOwner) {
                imageProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                imageRecycler.visibility = View.VISIBLE
                                imageArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.data){
                                    if(article.name.lowercase().contains(search)){
                                        imageArticles2.add(article)
                                    }
                                }
                                adapter = ImageAdapter(requireActivity(), imageArticles2)
                                imageRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                imageRecycler.visibility = View.VISIBLE
                                imageArticles2.clear()
                                imageArticles = it.data as MutableList<Resource>
                                adapter = ImageAdapter(requireActivity(), imageArticles)
                                imageRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    imageRecycler.visibility = View.VISIBLE
                    imageArticles = it.data as MutableList<Resource>
                    adapter = ImageAdapter(requireActivity(), imageArticles)
                    imageRecycler.adapter = adapter
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