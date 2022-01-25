package com.example.devlist.ui.fragments

import android.os.Bundle
<<<<<<< HEAD
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
=======
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.R
import com.example.devlist.data.api.ApiHelper
import com.example.devlist.data.api.ApiInterface
import com.example.devlist.data.model.DevResource
import com.example.devlist.data.model.Resource
import com.example.devlist.ui.adapters.ApiAdapter
import kotlinx.android.synthetic.main.public_api_fragment.*
import kotlinx.coroutines.DelicateCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PublicApiFragment : Fragment() {

    lateinit var adapter : ApiAdapter
    private var articles = mutableListOf<Resource>()
>>>>>>> 3def0dc (.)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
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
=======
        return inflater.inflate(R.layout.public_api_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       // viewModel = ViewModelProvider(this).get(PublicApiViewModel::class.java)

        adapter = ApiAdapter(requireActivity(),articles)
        apiRecycler.adapter = adapter
        apiRecycler.layoutManager = LinearLayoutManager(activity)

        getApiList()
    }

    @DelicateCoroutinesApi
    private fun getApiList(){
        val api = ApiHelper.getInstance().create(ApiInterface::class.java).getApiList("tools-and-utilities/public-apis")
        api.enqueue(object : Callback<DevResource> {
            override fun onFailure(call: Call<DevResource>, t: Throwable) {
                Log.d("DEV", "Error in Fetching list", t)
            }
            override fun onResponse(call: Call<DevResource>, response: Response<DevResource>) {
                val list = response.body()
                if (list != null) {
                    apiProgressBar.visibility = View.GONE
                    articles.addAll(list.resources)
                    adapter.notifyDataSetChanged()
                    Log.d("resouces count","${list.count}")
                }
                else{
                    Toast.makeText(activity, "The Response is empty", Toast.LENGTH_LONG).show()
                }
            }
        })
>>>>>>> 3def0dc (.)
    }

}