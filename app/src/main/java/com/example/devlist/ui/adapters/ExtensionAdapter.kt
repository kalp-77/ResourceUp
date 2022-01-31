package com.example.devlist.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.devlist.R
import com.example.devlist.data.model.Resource
import com.example.devlist.ui.webview.ExtensionWebViewActivity

class ExtensionAdapter(private val context: FragmentActivity, private val articles: List<Resource>): RecyclerView.Adapter<ExtensionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.extension_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        val animation = AnimationUtils.loadAnimation(holder.itemView.context,android.R.anim.slide_in_left)

        holder.extensionName.text = article.name
        holder.extensionDesc.text = article.description


        holder.itemView.setOnClickListener {
            val intent = Intent(context, ExtensionWebViewActivity::class.java)
            if(article.links.website == null){
                intent.putExtra("URL",article.links.gitHub)
            }
            else{
                intent.putExtra("URL", article.links.website)
            }
            context.startActivity(intent)
        }
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var extensionName: TextView = itemView.findViewById(R.id.extensionName)
        var extensionDesc: TextView = itemView.findViewById(R.id.extensionDesc)
    }

}