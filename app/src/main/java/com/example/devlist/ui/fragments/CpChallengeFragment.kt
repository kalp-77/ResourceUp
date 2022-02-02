package com.example.devlist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.CpChallengeFragmentBinding
import com.example.devlist.ui.adapters.CpChallengeAdapter
import com.example.devlist.ui.viewmodel.CpChallengeViewModel

class CpChallengeFragment : Fragment() {

    private var _binding: CpChallengeFragmentBinding? = null
    private val binding get() = _binding!!
    private val cpChallengeViewModel: CpChallengeViewModel by viewModels()
    private lateinit var adapter: CpChallengeAdapter
    private var cpArticles = mutableListOf<Resource>()

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
                    cpRecycler.visibility = View.VISIBLE
                    cpArticles = it.resources as MutableList<Resource>
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