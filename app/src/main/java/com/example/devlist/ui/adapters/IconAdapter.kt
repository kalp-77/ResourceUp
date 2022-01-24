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
import com.example.devlist.ui.webview.IconsWebViewActivity

class IconAdapter(private val context: FragmentActivity, private val articles: List<Resource>) :RecyclerView.Adapter<IconAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.icons_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.iconName.text = article.name
        holder.iconDesc.text = article.description


        holder.itemView.setOnClickListener {
            val intent = Intent(context, IconsWebViewActivity::class.java)
            intent.putExtra("URL",article.links.website)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    //View Holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iconName: TextView = itemView.findViewById(R.id.iconName)
        var iconDesc: TextView = itemView.findViewById(R.id.iconDesc)
    }

}