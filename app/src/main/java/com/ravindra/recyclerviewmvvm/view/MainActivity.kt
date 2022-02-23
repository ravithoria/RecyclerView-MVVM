package com.ravindra.recyclerviewmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ravindra.recyclerviewmvvm.viewmodel.MyViewModelFactory
import com.ravindra.recyclerviewmvvm.adapter.MainAdapter
import com.ravindra.recyclerviewmvvm.databinding.ActivityMainBinding
import com.ravindra.recyclerviewmvvm.repository.MainRepository
import com.ravindra.recyclerviewmvvm.viewmodel.MainViewModel
import com.ravindra.recyclerviewmvvm.network.ApiInterface
import com.ravindra.recyclerviewmvvm.network.RestClient

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var apiInterface: ApiInterface

    private var adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apiInterface = RestClient.client.create(ApiInterface::class.java)
        val mainRepository = MainRepository(apiInterface)

        /*val viewModelFactory = MyViewModelFactory(MainRepository(apiInterface))
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)*/
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository))
            .get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.moviesList.observe(this) {
            Log.d(TAG, "onCreate:: $it")
            //layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            adapter.setMovieList(it)
        }

        viewModel.errorMessage.observe(this, Observer {
        })
    }
}