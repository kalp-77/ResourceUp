package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.PublicApiFragmentBinding
import com.example.devlist.databinding.UiFragmentBinding
import com.example.devlist.ui.adapters.ApiAdapter
import com.example.devlist.ui.viewmodel.PublicApiViewModel
import kotlinx.android.synthetic.main.public_api_fragment.*

class PublicApiFragment : Fragment() {

    private var _binding: PublicApiFragmentBinding? = null
    private val binding get() = _binding!!

    private val apiViewModel: PublicApiViewModel by viewModels()

    lateinit var adapter : ApiAdapter
    private var publicApiArticles = mutableListOf<Resource>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout and bind to the _binding
        _binding = PublicApiFragmentBinding.inflate(inflater, container, false)

        // apply recycler adapter binding to the fragment
        binding.apply {
            apiRecycler.apply {
                apiRecycler.layoutManager = LinearLayoutManager(activity)
            }

            apiViewModel.apiLiveData.observe(this@PublicApiFragment.viewLifecycleOwner, {
                apiProgressBar.visibility = View.GONE
                if (it != null) {
                    apiRecycler.visibility = View.VISIBLE
                    publicApiArticles = it.resources as MutableList<Resource>
                    adapter = ApiAdapter(requireActivity(), publicApiArticles)
                    apiRecycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            })
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}