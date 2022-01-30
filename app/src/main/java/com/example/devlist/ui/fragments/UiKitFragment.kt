package com.example.devlist.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.UiKitFragmentBinding
import com.example.devlist.ui.adapters.UiKitAdapter
import com.example.devlist.ui.viewmodel.UiKitViewModel

class UiKitFragment : Fragment() {
    private var _binding: UiKitFragmentBinding? = null
    private val binding get() = _binding!!
    private val uiKitViewModel: UiKitViewModel by viewModels()
    lateinit var adapter: UiKitAdapter
    private var uiKitArticles = mutableListOf<Resource>()
    private var tempArticles = mutableListOf<Resource>()


    companion object{
        fun newInstance() = UiKitFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = UiKitFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            uiKitRecycler.apply {
                uiKitRecycler.layoutManager = LinearLayoutManager(activity)
            }

            uiKitViewModel.uiKitLiveData.observe(this@UiKitFragment.viewLifecycleOwner) {
                uiKitProgressBar.visibility = View.GONE
                if (it != null) {
                    uiKitRecycler.visibility = View.VISIBLE
                    uiKitArticles = it.resources as MutableList<Resource>
                    adapter = UiKitAdapter(requireActivity(), uiKitArticles)
                    uiKitRecycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            uiKitViewModel.tempLiveData.observe(this@UiKitFragment.viewLifecycleOwner) {
                uiKitProgressBar.visibility = View.GONE
                if (it != null) {
                    uiKitRecycler.visibility = View.VISIBLE
                    tempArticles = it.resources as MutableList<Resource>
                    adapter = UiKitAdapter(requireActivity(), tempArticles)
                    uiKitRecycler.adapter = adapter
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
