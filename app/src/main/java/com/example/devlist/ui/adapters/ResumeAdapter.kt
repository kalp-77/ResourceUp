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
import com.example.devlist.ui.webview.ResumeWebViewActivity

class ResumeAdapter(private val context: FragmentActivity, private val articles: List<Resource>): RecyclerView.Adapter<ResumeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.resume_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        val animation = AnimationUtils.loadAnimation(holder.itemView.context,android.R.anim.slide_in_left)


        holder.resumeName.text = article.name
        holder.resumeDesc.text = article.description


        holder.itemView.setOnClickListener {
            val intent = Intent(context, ResumeWebViewActivity::class.java)
            intent.putExtra("URL",article.links.website)
            context.startActivity(intent)
        }
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var resumeName: TextView = itemView.findViewById(R.id.resumeName)
        var resumeDesc: TextView = itemView.findViewById(R.id.resumeDesc)
    }

}