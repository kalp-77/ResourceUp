package com.example.devlist.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.UiFragmentBinding
import com.example.devlist.ui.adapters.UiAdapter
import com.example.devlist.ui.viewmodel.UiViewModel
import kotlinx.android.synthetic.main.public_api_fragment.*
import kotlinx.android.synthetic.main.ui_fragment.*

class UiFragment : Fragment() {

    // assign the _binding variable initially to null and
    // also when the view is destroyed again it has to be set to null
    private var _binding: UiFragmentBinding? = null

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding!!

    //uiViewModel object
    private val uiViewModel: UiViewModel by viewModels()

    lateinit var adapter: UiAdapter
    private var uiArticles = mutableListOf<Resource>()   // Contains Response from webserver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the layout and bind to the _binding
        _binding = UiFragmentBinding.inflate(inflater, container, false)

        // apply recycler adapter binding to the fragment
        binding.apply {
            uiRecycler.apply {
                uiRecycler.layoutManager = LinearLayoutManager(activity)
            }

            uiViewModel.uiLiveData.observe(this@UiFragment.viewLifecycleOwner) {
                uiProgressBar.visibility = View.GONE
                if (it != null) {
                    uiRecycler.visibility = View.VISIBLE
                    uiArticles = it.resources as MutableList<Resource>
                    adapter = UiAdapter(requireActivity(), uiArticles)
                    uiRecycler.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return binding.root
    }


    /**
     *  Without using coroutines and view model
     */
//    @DelicateCoroutinesApi
//    private fun getUiList() {
//        val api = ApiHelper.getInstance().create(ApiInterface::class.java).getUiList("ui-design/inspiration")
//        api.enqueue(object : Callback<DevResource> {
//            override fun onFailure(call: Call<DevResource>, t: Throwable) {
//                Log.d("DEV", "Error in Fetching list", t)
//            }
//            override fun onResponse(call: Call<DevResource>, response: Response<DevResource>) {
//                val list = response.body()
//                if (list != null) {
//                    uiArticles.addAll(list.resources)
//                    adapter.notifyDataSetChanged()
//                    Log.d("kalp","${list.count}")
//                } else {
//                    Toast.makeText(activity, "The Response is empty", Toast.LENGTH_LONG).show()
//                }
//            }
//        })
//     }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}