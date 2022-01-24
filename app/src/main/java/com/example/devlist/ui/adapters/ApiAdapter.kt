package com.example.devlist.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.devlist.R
import com.example.devlist.data.model.Resource
import com.example.devlist.ui.webview.PublicApiWebViewActivity
import com.example.devlist.ui.webview.UiWebViewActivityActivity

class ApiAdapter(private val context: FragmentActivity, private val articles: List<Resource>): RecyclerView.Adapter<ApiAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.public_api_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.apiType.text = article.apiCategory
        holder.apiName.text = article.name
        holder.apiDesc.text = article.description
        holder.apiCors.text = article.cors

        //for webpage view
        holder.itemView.setOnClickListener {
            val intent = Intent(context,PublicApiWebViewActivity::class.java)
            intent.putExtra("URL",article.links.website)
            context.startActivity(intent)
        }
        if(article.https){
            holder.apiHttps.text = "YES"
        }else{
            holder.apiHttps.text = "NO"
        }
        if(article.auth == ""){
            holder.apiAuth.text = "No"
        }else{
            holder.apiAuth.text = article.auth
        }

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    // viewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var apiName: TextView = itemView.findViewById(R.id.apiName)
        var apiDesc: TextView = itemView.findViewById(R.id.apiDesc)
        var apiType: TextView = itemView.findViewById(R.id.apiType)
        var apiAuth: TextView = itemView.findViewById(R.id.apiAuth)
        var apiCors: TextView = itemView.findViewById(R.id.apiCors)
        var apiHttps: TextView = itemView.findViewById(R.id.apiHttps)
    }


}