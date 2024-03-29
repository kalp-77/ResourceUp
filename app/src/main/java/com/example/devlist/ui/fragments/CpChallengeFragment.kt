package com.example.devlist.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.CpChallengeFragmentBinding
import com.example.devlist.ui.adapters.CpChallengeAdapter
import com.example.devlist.ui.adapters.FontAdapter
import com.example.devlist.ui.viewmodel.CpChallengeViewModel
import okhttp3.Challenge

class CpChallengeFragment : Fragment() {

    private var _binding: CpChallengeFragmentBinding? = null
    private val binding get() = _binding!!
    private val cpChallengeViewModel: CpChallengeViewModel by viewModels()
    private lateinit var adapter: CpChallengeAdapter
    private var cpArticles = mutableListOf<Resource>()
    private var cpArticles2 = mutableListOf<Resource>()


    companion object{
        fun newInstance() = CpChallengeFragment().apply {
            arguments = Bundle().apply {  }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CpChallengeFragmentBinding.inflate(inflater, container, false)

        binding.apply {
            cpRecycler.apply {
                cpRecycler.layoutManager = LinearLayoutManager(activity)
            }
            cpChallengeViewModel.cpLiveData.observe(this@CpChallengeFragment.viewLifecycleOwner) {
                cpProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnClickListener{
                        challenges.visibility = View.GONE
                    }
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if (p0!!.isNotEmpty()) {
                                cpRecycler.visibility = View.VISIBLE
                                cpArticles2.clear()
                                val search = p0.lowercase()
                                for (article in it.data) {
                                    if (article.name.lowercase().contains(search)) {
                                        cpArticles2.add(article)
                                    }
                                }
                                adapter = CpChallengeAdapter(requireActivity(), cpArticles2)
                                cpRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            } else {
                                cpRecycler.visibility = View.VISIBLE
                                cpArticles2.clear()
                                cpArticles = it.data as MutableList<Resource>
                                adapter = CpChallengeAdapter(requireActivity(), cpArticles)
                                cpRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    cpRecycler.visibility = View.VISIBLE
                    cpArticles = it.data as MutableList<Resource>
                    adapter = CpChallengeAdapter(requireActivity(), cpArticles)
                    cpRecycler.adapter = adapter
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