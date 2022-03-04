package com.example.devlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devlist.data.model.DevResource
import com.example.devlist.data.model.Resource
import com.example.devlist.databinding.PublicApiFragmentBinding
import com.example.devlist.ui.adapters.ApiAdapter
import com.example.devlist.ui.viewmodel.PublicApiViewModel
import kotlinx.android.synthetic.main.public_api_fragment.*

class PublicApiFragment : Fragment() {

    private var _binding: PublicApiFragmentBinding? = null
    private val binding get() = _binding!!
    private val apiViewModel: PublicApiViewModel by viewModels()
    lateinit var adapter : ApiAdapter
    private var publicApiArticles = mutableListOf<Resource>()
    private var publicApiArticles2 = mutableListOf<Resource>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PublicApiFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            apiRecycler.apply {
                apiRecycler.layoutManager = LinearLayoutManager(activity)
            }
            apiViewModel.apiLiveData.observe(this@PublicApiFragment.viewLifecycleOwner) {
                apiProgressBar.visibility = View.GONE
                val result: DevResource = it
                if (it != null) {
                    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return true
                        }
                        override fun onQueryTextChange(p0: String?): Boolean {
                            if(p0!!.isNotEmpty()){
                                apiRecycler.visibility = View.VISIBLE
                                publicApiArticles2.clear()
                                val search = p0.lowercase()
                                for(article in it.resources){
                                    if(article.name.lowercase().contains(search)){
                                        publicApiArticles2.add(article)
                                    }
                                }
                                adapter = ApiAdapter(requireActivity(), publicApiArticles2)
                                apiRecycler.adapter = adapter
                                adapter.notifyDataSetChanged()
                            }
                            else{
                                apiRecycler.visibility = View.VISIBLE
                                publicApiArticles2.clear()
                                publicApiArticles.addAll(it.resources)
                                change()
                            }
                            return true
                        }
                    })
                    apiRecycler.visibility = View.VISIBLE
                    publicApiArticles.addAll(it.resources)
                    change()
                }
                binding.listAll.setOnClickListener {
                    TV.text = "ALL CATEGORY"
                    publicApiArticles.clear()
                    publicApiArticles.addAll(result.resources)
                    change()
                }
                binding.crypto.setOnClickListener {
                    TV.text = "CRYPTOCURRENCY"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Cryptocurrency") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.vehicle.setOnClickListener {
                    TV.text = "VEHICLE"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Vehicle") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.animals.setOnClickListener {
                    TV.text = "ANIMALS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Animals") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.anime.setOnClickListener {
                    TV.text = "ANIME"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Anime") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.antiMalware.setOnClickListener {
                    TV.text = "ANTI-MALWARE"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Anti-Malware") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.artDesign.setOnClickListener {
                    TV.text = "ART-DESIGN"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Art & Design") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.auth.setOnClickListener {
                    TV.text = "AUTHENTICATION"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Authentication & Authorization") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.blockchain.setOnClickListener {
                    TV.text = "BLOCKCHAIN"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Blockchain") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.books.setOnClickListener {
                    TV.text = "BOOKS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Books") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.business.setOnClickListener {
                    TV.text = "BUSINESS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Business") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.calendar.setOnClickListener {
                    TV.text = "CALENDER"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Calendar") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.cloudStorage.setOnClickListener {
                    TV.text = "CLOUD-STORAGE"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Cloud Storage & File Sharing") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.continuousIntegration.setOnClickListener {
                    TV.text = "CONTINUOUS-INTEGRATION"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Continuous Integration") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.currencyExchange.setOnClickListener {
                    TV.text = "CURRENCY-EXCHANGE"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Currency Exchange") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.dataValidation.setOnClickListener {
                    TV.text = "DATA-VALIDATION"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Data Validation") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.development.setOnClickListener {
                    TV.text = "DEVELOPMENT"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Development") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.dictionaries.setOnClickListener {
                    TV.text = "DICTIONARIES"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Dictionaries") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.documents.setOnClickListener {
                    TV.text = "DOCUMENTS & PRODUCTIVITY"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Documents & Productivity") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.email.setOnClickListener {
                    TV.text = "EMAIL"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Email") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.entertainment.setOnClickListener {
                    TV.text = "ENTERTAINMENT"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Entertainment") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.env.setOnClickListener {
                    TV.text = "ENVIRONMENT"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Environment") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.events.setOnClickListener {
                    TV.text = "EVENTS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Events") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.finance.setOnClickListener {
                    TV.text = "FINANCE"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Finance") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.food.setOnClickListener {
                    TV.text = "FOOD & DRINKS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Food & Drink") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.games.setOnClickListener {
                    TV.text = "GAMES & COMICS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Games & Comics") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.geocoding.setOnClickListener {
                    TV.text = "GEOCODING"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Geocoding") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.gov.setOnClickListener {
                    TV.text = "GOVERNMENT"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Government") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.health.setOnClickListener {
                    TV.text = "HEALTH"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Health") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.jobs.setOnClickListener {
                    TV.text = "JOBS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Jobs") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.ml.setOnClickListener {
                    TV.text = "MACHINE LEARNING"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Machine Learning") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.music.setOnClickListener {
                    TV.text = "MUSIC"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Music") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.news.setOnClickListener {
                    TV.text = "NEWS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "News") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.openData.setOnClickListener {
                    TV.text = "OPEN DATA"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Open Data") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.openSource.setOnClickListener {
                    TV.text = "OPEN SOURCE PROJECTS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Open Source Projects") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.personality.setOnClickListener {
                    TV.text = "PERSONALITY"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Personality") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.phone.setOnClickListener {
                    TV.text = "PHONE"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Phone") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.photo.setOnClickListener {
                    TV.text = "PHOTOGRAPHY"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Photography") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.programming.setOnClickListener {
                    TV.text = "PROGRAMMING"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Programming") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.science.setOnClickListener {
                    TV.text = "SCIENCE & MATHS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Science & Math") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.security.setOnClickListener {
                    TV.text = "SECURITY"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Security") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.shopping.setOnClickListener {
                    TV.text = "SHOPPING"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Shopping") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.social.setOnClickListener {
                    TV.text = "SOCIAL"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Social") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.sports.setOnClickListener {
                    TV.text = "SPORTS & FITNESS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Sports & Fitness") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.testData.setOnClickListener {
                    TV.text = "TEST DATA"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Test Data") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.textAnalysis.setOnClickListener {
                    TV.text = "TEXT ANALYSIS"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Text Analysis") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.tracking.setOnClickListener {
                    TV.text = "TRACKING"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Tracking") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.transport.setOnClickListener {
                    TV.text = "TRANSPORTATION"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Transportation") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.video.setOnClickListener {
                    TV.text = "VIDEO"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Video") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
                binding.weather.setOnClickListener {
                    TV.text = "WEATHER"
                    publicApiArticles.clear()
                    for (article in result.resources) {
                        if (article.apiCategory == "Weather") {
                            publicApiArticles.add(article)
                        }
                    }
                    change()
                }
            }
        }
        return binding.root
    }

    private fun change(){
        adapter = ApiAdapter(requireActivity(),publicApiArticles)
        apiRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}