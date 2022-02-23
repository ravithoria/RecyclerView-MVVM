package com.ravindra.recyclerviewmvvm.repository

import androidx.lifecycle.MutableLiveData
import com.ravindra.recyclerviewmvvm.model.Movie
import com.ravindra.recyclerviewmvvm.network.ApiInterface
import retrofit2.Call
import retrofit2.Response

class MainRepository constructor(private val apiInterface: ApiInterface) {

    //fun getAllMovies() = apiInterface.getAllMovies()

    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    fun moviesListAPI() {
        val call = apiInterface.getAllMovies()
        //val call = mApiService!!.fetchUser()

        call.enqueue(object : retrofit2.Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {

                movieList.postValue(response.body())
                //val userResponse = response.body()!!
                //Log.d(TAG, "data_size:: " + userResponse.results?.size)
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                errorMessage.postValue(t.message)
                //Toast
                //Toast.makeText(this@MainActivity, "Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}