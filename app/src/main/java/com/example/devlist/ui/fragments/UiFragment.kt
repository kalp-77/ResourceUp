package com.example.devlist.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.dataSource.ImgDataSrc
import com.example.devlist.data.model.ImgItem
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.UiFragmentBinding
import com.example.devlist.ui.adapters.IconAdapter
import com.example.devlist.ui.adapters.UiAdapter
import com.example.devlist.ui.viewmodel.UiViewModel

class UiFragment : Fragment() {

    private var _binding: UiFragmentBinding? = null
    private val binding get() = _binding!!
    private val uiViewModel: UiViewModel by viewModels()
    lateinit var adapter: UiAdapter
    private var uiArticles = mutableListOf<Resource>()
    private var uiArticles2 = mutableListOf<Resource>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = UiFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            uiRecycler.apply {
                uiRecycler.layoutManager = LinearLayoutManager(activity)
            }
            uiViewModel.uiLiveData.observe(this@UiFragment.viewLifecycleOwner) {
                uiProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                uiRecycler.visibility = View.VISIBLE
                                uiArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.resources){
                                    if(article.name.lowercase().contains(search)){
                                        uiArticles2.add(article)
                                    }
                                }
                                val myDataset = ImgDataSrc().loadImg()
                                adapter = UiAdapter(requireActivity(), uiArticles2,myDataset)
                                uiRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                uiRecycler.visibility = View.VISIBLE
                                uiArticles2.clear()
                                uiArticles = it.resources as MutableList<Resource>
                                val myDataset = ImgDataSrc().loadImg()
                                adapter = UiAdapter(requireActivity(), uiArticles,myDataset)
                                uiRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    uiRecycler.visibility = View.VISIBLE
                    uiArticles = it.resources as MutableList<Resource>
                    val myDataset = ImgDataSrc().loadImg()
                    adapter = UiAdapter(requireActivity(), uiArticles,myDataset)
                    uiRecycler.adapter = adapter
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