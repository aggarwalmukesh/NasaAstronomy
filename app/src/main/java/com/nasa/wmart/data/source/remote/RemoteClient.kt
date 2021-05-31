package com.nasa.wmart.data.source.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteClient {
    private var retrofit: Retrofit ?= null
    const val BASE_URL = "https://api.nasa.gov/planetary/"
    fun getRetrofitInstance(): Retrofit?{
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}