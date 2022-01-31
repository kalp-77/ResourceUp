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
import com.example.devlist.ui.webview.ImageWebViewActivity

class ImageAdapter (private val context: FragmentActivity, private val articles: List<Resource>):RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.imageName.text = article.name
        holder.imageDesc.text = article.description

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ImageWebViewActivity::class.java)
            if(article.links.website == null){
                intent.putExtra("URL",article.links.gitHub)
            }
            else{
                intent.putExtra("URL", article.links.website)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageName: TextView = itemView.findViewById(R.id.imageName)
        var imageDesc: TextView = itemView.findViewById(R.id.imageDesc)
    }


}