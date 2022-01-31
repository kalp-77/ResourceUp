package com.example.devlist.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.R
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.BoardsFragmentBinding
import com.example.devlist.ui.adapters.BoardsAdapter
import com.example.devlist.ui.viewmodel.BoardsViewModel

class BoardsFragment : Fragment() {

    private var _binding: BoardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val boardsViewModel: BoardsViewModel by viewModels()
    private lateinit var adapter: BoardsAdapter
    private var boardsArticles = mutableListOf<Resource>()

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
                    boardsRecycler.visibility = View.VISIBLE
                    boardsArticles = it.resources as MutableList<Resource>
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