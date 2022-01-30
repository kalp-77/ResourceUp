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
import com.example.devlist.databinding.FontFragmentBinding
import com.example.devlist.ui.adapters.FontAdapter
import com.example.devlist.ui.viewmodel.FontViewModel
import kotlinx.android.synthetic.main.font_fragment.*

class FontFragment : Fragment() {

    private var _binding: FontFragmentBinding? = null
    private val binding get() = _binding!!
    private val fontViewModel: FontViewModel by viewModels()

    lateinit var adapter: FontAdapter
    private var fontArticles = mutableListOf<Resource>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the layout and bind to the _binding
        _binding = FontFragmentBinding.inflate(inflater, container, false)

        // apply recycler adapter binding to the fragment
        binding.apply {
            fontRecycler.apply {
                fontRecycler.layoutManager = LinearLayoutManager(activity)
            }

            fontViewModel.fontLiveData.observe(this@FontFragment.viewLifecycleOwner) {
                fontProgressBar.visibility = View.GONE
                if (it != null) {
                    fontRecycler.visibility = View.VISIBLE
                    fontArticles = it.resources as MutableList<Resource>
                    adapter = FontAdapter(requireActivity(), fontArticles)
                    fontRecycler.adapter = adapter
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