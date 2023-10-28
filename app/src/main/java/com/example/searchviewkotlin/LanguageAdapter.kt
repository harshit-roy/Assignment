package com.example.searchviewkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageAdapter(var mList: List<LanguageData>) :
    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
        val description : TextView = itemView.findViewById(R.id.description)
        val link : TextView = itemView.findViewById(R.id.link)

    }

    fun setFilteredList(mList: List<LanguageData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item , parent , false)
        return LanguageViewHolder(view)
    }
    private var isEnable = false
    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.logo.setImageResource(mList[position].logo)
        holder.titleTv.text = mList[position].title
        holder.description.text = mList[position].description
        holder.link.text = mList[position].link


    }

    override fun getItemCount(): Int {
        return mList.size
    }
}