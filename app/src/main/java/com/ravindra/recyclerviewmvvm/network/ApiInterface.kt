package com.ravindra.recyclerviewmvvm.network

import com.ravindra.recyclerviewmvvm.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("movielist.json")
    fun getAllMovies(): Call<List<Movie>>
}