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
import com.example.devlist.databinding.ImageFragmentBinding
import com.example.devlist.databinding.LogoFragmentBinding
import com.example.devlist.ui.adapters.FontAdapter
import com.example.devlist.ui.adapters.LogoAdapter
import com.example.devlist.ui.viewmodel.ImageViewModel
import com.example.devlist.ui.viewmodel.LogoViewModel
import kotlinx.android.synthetic.main.logo_fragment.*

class LogoFragment : Fragment() {

    private var _binding: LogoFragmentBinding? = null
    private val binding get() = _binding!!
    private val logoViewModel: LogoViewModel by viewModels()

    lateinit var adapter: LogoAdapter
    private var logoArticles = mutableListOf<Resource>()
    private var logoArticles2 = mutableListOf<Resource>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LogoFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            logoRecycler.apply {
                logoRecycler.layoutManager = LinearLayoutManager(activity)
            }

            logoViewModel.logoLiveData.observe(this@LogoFragment.viewLifecycleOwner) {
                logoProgressBar.visibility = View.GONE
                if (it != null) {
                    logoRecycler.visibility = View.VISIBLE
                    logoArticles = it.resources as MutableList<Resource>
                    adapter = LogoAdapter(requireActivity(), logoArticles)
                    logoRecycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            logoViewModel.logoLiveData2.observe(this@LogoFragment.viewLifecycleOwner) {
                if (it != null) {
                    logoArticles2 = it.resources as MutableList<Resource>
                    adapter = LogoAdapter(requireActivity(), logoArticles2)
                    logoRecycler.adapter = adapter
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