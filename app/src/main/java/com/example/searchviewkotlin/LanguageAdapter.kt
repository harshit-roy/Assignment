package com.example.searchviewkotlin

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageAdapter(var mList: List<Item>) :
    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    private var TAG: String = "CHECK_RESPONSE"

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)
        val description: TextView = itemView.findViewById(R.id.description)
        val link: TextView = itemView.findViewById(R.id.link)

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
        Log.i(TAG, "onResponse: ${mList[position].owner.avatar_url}")

        holder.logo.setImageURI(Uri.parse(mList[position].owner.avatar_url))
        holder.titleTv.text = mList[position].full_name
        holder.link.text = mList[position].html_url
        holder.description.text = mList[position].description
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}