package com.ravindra.recyclerviewmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ravindra.recyclerviewmvvm.model.Movie
import com.ravindra.recyclerviewmvvm.repository.MainRepository
import androidx.lifecycle.LiveData

class MainViewModel constructor(private val repository: MainRepository): ViewModel() {

    init {
        Log.i("MainViewModel", "MainViewModel created!")
        repository.moviesListAPI()
    }

    val moviesList : LiveData<List<Movie>>
    get() = repository.movieList

    val errorMessage : LiveData<String>
    get() = repository.errorMessage

    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel", "MainViewModel destroyed!")
    }

    /*val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    fun moviesListAPI() {
        val call = mApiInterface.getAllMovies()
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
    }*/

}