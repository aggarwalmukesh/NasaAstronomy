package com.nasa.wmart.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nasa.wmart.data.source.NasaApiDataSource
import com.nasa.wmart.model.NasaInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NasaApiRemoteDataSource : NasaApiDataSource {
    private var mutableLiveData: MutableLiveData<NasaInfo> = MutableLiveData()

    override fun fetchNasaApodData() {
        val apiEndPoints = RemoteClient.getRetrofitInstance()?.create(ApiEndPoints::class.java)

        apiEndPoints?.getNasaInfo(NASA_API_KEY)?.enqueue(object : Callback<NasaInfo> {
            override fun onResponse(
                call: Call<NasaInfo>,
                response: Response<NasaInfo>
            ) {
                Log.v(TAG, "onResoponse "+ response.body());
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<NasaInfo>, t: Throwable) {
                //mutableLiveData.value =
                Log.v(TAG, "onFailure");
            }

        })
    }

    fun getNasaInfoData() = mutableLiveData

    companion object {
        private val TAG = NasaApiRemoteDataSource::class.java.canonicalName
        private val NASA_API_KEY = "vT99zqMDFSxLT8UebC4FUMcvLpGWOWLek3Ix8xAA"
    }
}