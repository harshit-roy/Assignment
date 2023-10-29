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
    private var mList = ArrayList<Item>()
    private lateinit var adapter: LanguageAdapter
    private val BASE_URL = "https://api.github.com/"
    private var TAG:String = "CHECK_RESPONSE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAllData()
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


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

    private fun getAllData(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getData().enqueue(object : Callback<LanguageData>{
            override fun onResponse(
                call: Call<LanguageData>,
                response: Response<LanguageData>)

            {
                if(response.isSuccessful){
                    response.body()?.items?.let{
                        for(items in it){
                            Log.i(TAG, "onResponse: ${Owner(items.owner.avatar_url)}")
                            mList.add(Item(items.full_name,Owner(items.owner.avatar_url),items.html_url,items.description))
                        }
                    }

                }
                adapter = LanguageAdapter(mList)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<LanguageData>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }
        })
    }




    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<Item>()
            for (i in mList) {
                if (i.full_name.lowercase(Locale.ROOT).contains(query)) {
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

}