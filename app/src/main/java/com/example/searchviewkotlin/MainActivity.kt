package com.example.searchviewkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(LanguageData("Cpp", R.drawable.java,"Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("C", R.drawable.java,"Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Python", R.drawable.java,"Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Kotlin", R.drawable.java,"Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("React", R.drawable.java,"Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Java", R.drawable.java,"Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Java", R.drawable.java,"chart_with_upwards_trend: Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Java", R.drawable.java,"chart_with_upwards_trend: Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Java", R.drawable.java,"chart_with_upwards_trend: Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Java", R.drawable.java,"chart_with_upwards_trend: Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Java", R.drawable.java,"chart_with_upwards_trend: Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
        mList.add(LanguageData("Java", R.drawable.java,"chart_with_upwards_trend: Capturing JVM- and application-level metrics. So you know what's going on.","https://github.com/dropwizard/metrics"))
    }

}