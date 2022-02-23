package com.ravindra.recyclerviewmvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    private const val BASE_URL = "https://howtodoandroid.com/"
    private var mRetrofit: Retrofit? = null

    val client: Retrofit
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return mRetrofit!!
        }
}