package com.example.searchviewkotlin

import retrofit2.Call
import retrofit2.http.GET
interface MyApi  {
    @GET("search/repositories?q=trending&sort=stars")
    fun getData(): Call<LanguageData>
}