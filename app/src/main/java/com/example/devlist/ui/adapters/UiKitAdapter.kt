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
import com.example.devlist.data.model.Resource
import com.example.devlist.ui.webview.ImageWebViewActivity

class UiKitAdapter(private val context: FragmentActivity, private val articles: List<Resource>): RecyclerView.Adapter<UiKitAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.ui_kit_layout, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val article = articles[position]
            val animation = AnimationUtils.loadAnimation(holder.itemView.context,android.R.anim.fade_in)

            holder.uiKitName.text = article.name
            holder.uiKitDesc.text = article.description
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
            holder.itemView.startAnimation(animation)
            holder.share.setOnClickListener{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                val link :String = article.links.website
                val body = "Look at this !! $link"
                intent.putExtra(Intent.EXTRA_TEXT,link)
                intent.putExtra(Intent.EXTRA_TEXT,body)
                context.startActivity(Intent.createChooser(intent,"share"))
            }
        }

        override fun getItemCount(): Int {
            return articles.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var uiKitName: TextView = itemView.findViewById(R.id.uiKitName)
            var uiKitDesc: TextView = itemView.findViewById(R.id.uiKitDesc)
            var share : ImageView = itemView.findViewById(R.id.share)
        }
}