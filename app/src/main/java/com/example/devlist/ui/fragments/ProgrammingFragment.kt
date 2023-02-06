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
import com.example.devlist.databinding.ProgrammingFragmentBinding
import com.example.devlist.ui.adapters.ProgrammingAdapter
import com.example.devlist.ui.adapters.UiAdapter
import com.example.devlist.ui.viewmodel.ProgrammingViewModel

class ProgrammingFragment : Fragment() {

    private var _binding: ProgrammingFragmentBinding? = null
    private val binding get() = _binding!!
    private val proViewModel: ProgrammingViewModel by viewModels()
    lateinit var adapter: ProgrammingAdapter
    private var proArticles = mutableListOf<Resource>()
    private var proArticles2 = mutableListOf<Resource>()


    companion object{
        fun newInstance() = ProgrammingFragment().apply {
            arguments = Bundle().apply {  }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProgrammingFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            proRecycler.apply {
                proRecycler.layoutManager = LinearLayoutManager(activity)
            }
            proViewModel.programmingLiveData.observe(this@ProgrammingFragment.viewLifecycleOwner) {
                proProgressBar.visibility = View.GONE
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                proRecycler.visibility = View.VISIBLE
                                proArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.data){
                                    if(article.name.lowercase().contains(search)){
                                        proArticles2.add(article)
                                    }
                                }
                                adapter = ProgrammingAdapter(requireActivity(), proArticles2)
                                proRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                proRecycler.visibility = View.VISIBLE
                                proArticles2.clear()
                                proArticles = it.data as MutableList<Resource>
                                adapter = ProgrammingAdapter(requireActivity(), proArticles,)
                                proRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            return true
                        }
                    })
                    proRecycler.visibility = View.VISIBLE
                    proArticles = it.data as MutableList<Resource>
                    adapter = ProgrammingAdapter(requireActivity(), proArticles)
                    proRecycler.adapter = adapter
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