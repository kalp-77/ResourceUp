package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.BoardsFragmentBinding
import com.example.devlist.ui.adapters.BoardsAdapter
import com.example.devlist.ui.adapters.FontAdapter
import com.example.devlist.ui.viewmodel.BoardsViewModel

class BoardsFragment : Fragment() {

    private var _binding: BoardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val boardsViewModel: BoardsViewModel by viewModels()
    private lateinit var adapter: BoardsAdapter
    private var boardsArticles = mutableListOf<Resource>()
    private var boardsArticles2 = mutableListOf<Resource>()  // contains searched items


    companion object{
        fun newInstance() = BoardsFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BoardsFragmentBinding.inflate(inflater, container, false)

        binding.apply {
            boardsRecycler.apply {
                boardsRecycler.layoutManager = LinearLayoutManager(activity)
            }
            boardsViewModel.boardsLiveData.observe(this@BoardsFragment.viewLifecycleOwner) {
                boardsProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                boardsRecycler.visibility = View.VISIBLE
                                boardsArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.data){
                                    if(article.name.lowercase().contains(search)){
                                        boardsArticles2.add(article)
                                    }
                                }
                                adapter = BoardsAdapter(requireActivity(), boardsArticles2)
                                boardsRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                boardsRecycler.visibility = View.VISIBLE
                                boardsArticles2.clear()
                                boardsArticles = it.data as MutableList<Resource>
                                adapter = BoardsAdapter(requireActivity(), boardsArticles)
                                boardsRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    boardsRecycler.visibility = View.VISIBLE
                    boardsArticles = it.data as MutableList<Resource>
                    adapter = BoardsAdapter(requireActivity(), boardsArticles)
                    boardsRecycler.adapter = adapter
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