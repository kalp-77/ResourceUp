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
import com.example.devlist.ui.adapters.ResumeAdapter
import com.example.devlist.ui.viewmodel.ResumeViewModel

class ResumeFragment : Fragment() {
    private var _binding: ResumeFragmentBinding? = null
    private val binding get() = _binding!!
    private val resumeViewModel: ResumeViewModel by viewModels()
    lateinit var adapter: ResumeAdapter
    private var resumeArticles = mutableListOf<Resource>()

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
                    resumeRecycler.visibility = View.VISIBLE
                    resumeArticles = it.resources as MutableList<Resource>
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