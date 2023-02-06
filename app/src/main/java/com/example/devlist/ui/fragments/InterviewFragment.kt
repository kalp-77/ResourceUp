package com.example.devlist.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.InterviewFragmentBinding
import com.example.devlist.ui.adapters.IconAdapter
import com.example.devlist.ui.adapters.InterviewAdapter
import com.example.devlist.ui.viewmodel.InterviewViewModel
import kotlinx.android.synthetic.main.interview_fragment.*

class InterviewFragment : Fragment() {

    private var _binding: InterviewFragmentBinding? = null
    private val binding get() = _binding!!
    private val interviewViewModel: InterviewViewModel by viewModels()
    private lateinit var adapter: InterviewAdapter
    private var interviewArticles = mutableListOf<Resource>()
    private var interviewArticles2 = mutableListOf<Resource>()


    companion object{
        fun newInstance() = InterviewFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InterviewFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            interviewRecycler.apply {
                interviewRecycler.layoutManager = LinearLayoutManager(activity)
            }
            interviewViewModel.interviewLiveData.observe(this@InterviewFragment.viewLifecycleOwner) {
                interviewProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                interviewRecycler.visibility = View.VISIBLE
                                interviewArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.data){
                                    if(article.name.lowercase().contains(search)){
                                        interviewArticles2.add(article)
                                    }
                                }
                                adapter = InterviewAdapter(requireActivity(), interviewArticles2)
                                interviewRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                interviewRecycler.visibility = View.VISIBLE
                                interviewArticles2.clear()
                                interviewArticles = it.data as MutableList<Resource>
                                adapter = InterviewAdapter(requireActivity(), interviewArticles)
                                interviewRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    interviewRecycler.visibility = View.VISIBLE
                    interviewArticles = it.data as MutableList<Resource>
                    adapter = InterviewAdapter(requireActivity(), interviewArticles)
                    interviewRecycler.adapter = adapter
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