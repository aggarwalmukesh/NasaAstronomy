package com.nasa.wmart.data.source.remote

import com.nasa.wmart.model.NasaInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoints {

    @GET("apod")
    fun getNasaInfo(@Query("api_key") api_key: String): Call<NasaInfo>
}