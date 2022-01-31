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
import com.example.devlist.ui.adapters.InterviewAdapter
import com.example.devlist.ui.viewmodel.InterviewViewModel
import kotlinx.android.synthetic.main.interview_fragment.*

class InterviewFragment : Fragment() {
    private var _binding: InterviewFragmentBinding? = null
    private val binding get() = _binding!!
    private val interviewViewModel: InterviewViewModel by viewModels()

    private lateinit var adapter: InterviewAdapter
    private var interviewArticles = mutableListOf<Resource>()

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

        // apply recycler adapter binding to the fragment
        binding.apply {
            interviewRecycler.apply {
                interviewRecycler.layoutManager = LinearLayoutManager(activity)
            }

            interviewViewModel.interviewLiveData.observe(this@InterviewFragment.viewLifecycleOwner) {
                interviewProgressBar.visibility = View.GONE
                if (it != null) {
                    interviewRecycler.visibility = View.VISIBLE
                    interviewArticles = it.resources as MutableList<Resource>
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