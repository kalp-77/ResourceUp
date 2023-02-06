package com.example.devlist.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.devlist.R
import com.example.devlist.data.model.Entry
import com.example.devlist.data.model.Resource
import com.example.devlist.ui.webview.PublicApiWebViewActivity

class ApiAdapter(private val context: FragmentActivity, private val articles: List<Entry>): RecyclerView.Adapter<ApiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.public_api_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        //card animation
        val animation = AnimationUtils.loadAnimation(holder.itemView.context,android.R.anim.fade_in)

        holder.apiType.text = "Type : " + article.Category
        holder.apiName.text = article.API
        holder.apiDesc.text = article.Description

        // for sharing the link
        holder.share.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val link :String = article.Link
            val body = "Look at this !! $link"
            intent.putExtra(Intent.EXTRA_TEXT,link)
            intent.putExtra(Intent.EXTRA_TEXT,body)
            context.startActivity(Intent.createChooser(intent,"share"))
        }

        //for webpage view
        holder.itemView.setOnClickListener {
            val intent = Intent(context,PublicApiWebViewActivity::class.java)
            intent.putExtra("URL",article.Link)
            context.startActivity(intent)
        }
        if(article.HTTPS){
            holder.apiHttps.text = "Https : Yes"
        }else{
            holder.apiHttps.text = "Https : No"
        }
        if(article.Auth == ""){
            holder.apiAuth.text = "Auth : No"
        }else{
            holder.apiAuth.text = "Auth : " + article.Auth
        }

        //apply animation to holder
        holder.itemView.startAnimation(animation)
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
        var apiHttps: TextView = itemView.findViewById(R.id.apiHttps)
        var share : ImageView = itemView.findViewById(R.id.share)
    }



}