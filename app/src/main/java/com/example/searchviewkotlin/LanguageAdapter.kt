package com.example.searchviewkotlin

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LanguageAdapter(var mList: List<Item>) :
    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    private var TAG: String = "CHECK_RESPONSE"
    private val selectedItems = mutableListOf<Item>()
    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)
        val description: TextView = itemView.findViewById(R.id.description)
        val link: TextView = itemView.findViewById(R.id.link)
        val card: ConstraintLayout = itemView.findViewById(R.id.card)
    }

    fun setFilteredList(mList: List<Item>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)

        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {

        val context =holder.itemView.context
        Glide.with(context).load(mList[position].owner.avatar_url).into(holder.logo)

        holder.link.text = mList[position].html_url
        holder.description.text = mList[position].description

        val currentItem = mList[position]
        holder.titleTv.text = currentItem.full_name

        if (selectedItems.contains(currentItem)) {
            holder.titleTv.setTextColor(Color.parseColor("green"))
        } else {
            holder.titleTv.setTextColor(Color.parseColor("black"))
        }

        holder.card.setOnClickListener {

            if (selectedItems.contains(currentItem)) {
                selectedItems.remove(currentItem)
            } else {
                selectedItems.add(currentItem)
            }
            notifyDataSetChanged()
        }

        holder.link.setOnClickListener {
            val url = mList[position].html_url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}