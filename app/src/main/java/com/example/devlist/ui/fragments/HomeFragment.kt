package com.example.devlist.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import com.example.devlist.LoginActivity
import com.example.devlist.R
import com.example.devlist.databinding.HomeFragmentBinding
import com.example.devlist.databinding.HomeFragmentBinding.inflate
import com.example.devlist.ui.AssetActivity
import com.example.devlist.ui.JobActivity
import com.example.devlist.ui.LearnActivity
import com.example.devlist.ui.UtilityActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
//    lateinit var preferences: SharedPreferences
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        checkUser()







//        val preferences = requireActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
//        val name= preferences.getString("NAME","")
//        nameTv.text= name

        // pop up option menu
        logout.setOnClickListener{
            val popup = PopupMenu(requireContext(), it)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.action, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.logout -> {
                        auth.signOut()
                        checkUser()
//                        val editor: SharedPreferences.Editor = preferences.edit()
//                        editor.clear()
//                        editor.apply()
//                        val intent = Intent(this@HomeFragment.requireContext(), LoginActivity::class.java)
//                        startActivity(intent)
                    }
                }
                true
            }
            popup.show()
        }

        // navController object
        val navController = Navigation.findNavController(view)

        apiBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_publicApiFragment)
        }
        uiBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_uiFragment)
        }
        assetsBtn.setOnClickListener {
            val intent = Intent(context, AssetActivity::class.java)
            startActivity(intent)
        }
        learnBtn.setOnClickListener {
            val intent = Intent(context, LearnActivity::class.java)
            startActivity(intent)
        }
        jobBtn.setOnClickListener {
            val intent = Intent(context, JobActivity::class.java)
            startActivity(intent)
        }
        utilitiesBtn.setOnClickListener {
            val intent = Intent(context, UtilityActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkUser() {
        val firebaseUser = auth.currentUser
        if(firebaseUser!=null){
            var display : TextView = binding.nameTv
            var id = FirebaseAuth.getInstance().currentUser?.uid.toString()

            FirebaseFirestore.getInstance().collection("users").get()
                .addOnCompleteListener() {
                    val result : StringBuffer = StringBuffer()
                    if(it.isSuccessful){
                        for(document in it.result){
                            var it_id = document.get("user_UID")

                            if(it_id.toString()==id) {
                                result.append("Hi, ").append(document.data.getValue("Name"))
                            }
                        }
                        display.text = result
                    }
                }
        }
        else{
            val intent = Intent(this@HomeFragment.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}