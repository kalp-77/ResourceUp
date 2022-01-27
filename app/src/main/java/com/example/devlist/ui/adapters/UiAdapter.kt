package com.example.devlist.ui.adapters

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.devlist.R
import com.example.devlist.data.model.Resource
import com.example.devlist.ui.webview.UiWebViewActivity

class UiAdapter(private val context: FragmentActivity, private val articles: List<Resource>): RecyclerView.Adapter<UiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ui_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.uiName.text = article.name
        holder.uiDesc.text = article.description

        // for webpage view
        holder.itemView.setOnClickListener {
            val intent = Intent(context,UiWebViewActivity::class.java)
            intent.putExtra("URL",article.links.website)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    // viewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uiName: TextView = itemView.findViewById(R.id.uiName)
        var uiDesc: TextView = itemView.findViewById(R.id.uiDesc)
    }

}
