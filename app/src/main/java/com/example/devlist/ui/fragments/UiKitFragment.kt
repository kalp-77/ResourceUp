package com.example.devlist.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.dataSource.ImgDataSrc
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.UiKitFragmentBinding
import com.example.devlist.ui.adapters.UiAdapter
import com.example.devlist.ui.adapters.UiKitAdapter
import com.example.devlist.ui.viewmodel.UiKitViewModel

class UiKitFragment : Fragment() {

    private var _binding: UiKitFragmentBinding? = null
    private val binding get() = _binding!!
    private val uiKitViewModel: UiKitViewModel by viewModels()
    lateinit var adapter: UiKitAdapter
    private var uiKitArticles1 = mutableListOf<Resource>()
    private var uiKitArticles2 = mutableListOf<Resource>()

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
                    uiKitArticles1.addAll(it.data)
                }
            }
            uiKitViewModel.tempLiveData.observe(this@UiKitFragment.viewLifecycleOwner) {
                uiKitProgressBar.visibility = View.GONE
                if (it != null) {
                    uiKitArticles1.addAll(it.data)
                }
            }
            adapter = UiKitAdapter(requireActivity(), uiKitArticles1)
            uiKitRecycler.adapter = adapter
            adapter.notifyDataSetChanged()
            searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    if(p0!!.isNotEmpty()){
                        uiKitRecycler.visibility = View.VISIBLE
                        uiKitArticles2.clear()
                        val search = p0.lowercase()
                        for(article in uiKitArticles1){
                            if(article.name.lowercase().contains(search)){
                                uiKitArticles2.add(article)
                            }
                        }
                        adapter = UiKitAdapter(requireActivity(), uiKitArticles2)
                        uiKitRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    else{
                        uiKitRecycler.visibility = View.VISIBLE
                        uiKitArticles2.clear()
                        adapter = UiKitAdapter(requireActivity(), uiKitArticles1)
                        uiKitRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    return true
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
