package com.nasa.wmart.data.source.remote

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nasa.wmart.NasaApplication
import com.nasa.wmart.data.source.NasaApiDataSource
import com.nasa.wmart.model.NasaInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NasaApiRemoteDataSource(private val sharedPreferences: SharedPreferences) :
    NasaApiDataSource {
    private var mutableLiveData: MutableLiveData<NasaInfo> = MutableLiveData()

    override fun fetchNasaApodData() {

        val apiEndPoints = RemoteClient.getRetrofitInstance()?.create(ApiEndPoints::class.java)

        apiEndPoints?.getNasaInfo(NASA_API_KEY)?.enqueue(object : Callback<NasaInfo> {
            override fun onResponse(
                call: Call<NasaInfo>,
                response: Response<NasaInfo>
            ) {
                var nasaInfo = response.body()
                Log.v(TAG, "onResoponse " + nasaInfo)
                if (response.body() != null) {
                    //              db?.nasaDao()?.insertNasaInfo(response.body()!!)
                    sharedPreferences.edit().apply {
                        putString(NasaApplication.URL, nasaInfo?.url)
                        putString(NasaApplication.TITLE, nasaInfo?.title)
                        putString(NasaApplication.DESCRIPTION, nasaInfo?.explanation)
                        apply()
                    }
                    mutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<NasaInfo>, t: Throwable) {
                mutableLiveData.postValue(null)
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