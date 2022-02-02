package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.IconsFragmentBinding
import com.example.devlist.ui.adapters.IconAdapter
import com.example.devlist.ui.viewmodel.IconsViewModel
import kotlinx.android.synthetic.main.icons_fragment.*

class IconsFragment : Fragment() {

    private var _binding: IconsFragmentBinding? = null
    private val binding get() = _binding!!
    private val iconViewModel: IconsViewModel by viewModels()
    private lateinit var adapter: IconAdapter
    private var iconArticles = mutableListOf<Resource>()

    companion object{
        fun newInstance() = IconsFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = IconsFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            iconRecycler.apply {
                iconRecycler.layoutManager = LinearLayoutManager(activity)
            }
            iconViewModel.iconLiveData.observe(this@IconsFragment.viewLifecycleOwner) {
                iconProgressBar.visibility = View.GONE
                if (it != null) {
                    iconRecycler.visibility = View.VISIBLE
                    iconArticles = it.resources as MutableList<Resource>
                    adapter = IconAdapter(requireActivity(), iconArticles)
                    iconRecycler.adapter = adapter
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