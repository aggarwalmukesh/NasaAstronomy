package com.nasa.wmart.viewmodel

import android.content.SharedPreferences
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nasa.wmart.NasaApplication
import com.nasa.wmart.data.source.remote.NasaApiRemoteDataSource
import com.nasa.wmart.model.NasaInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetAddress
import java.net.UnknownHostException

class NasaViewModel(
    private val sharedPreferences: SharedPreferences,
    private val dataSource: NasaApiRemoteDataSource = NasaApiRemoteDataSource(sharedPreferences)
) : ViewModel() {
    private var nasaInfo: MutableLiveData<NasaInfo> = dataSource.getNasaInfoData()
    private var mutableIsConnectedToInternet: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchNasaImageData() {
        CoroutineScope(Dispatchers.Main).launch {
            val isConnected = isConnectedToInternet()
            if (isConnected) {
                mutableIsConnectedToInternet.value = true
                dataSource.fetchNasaApodData()
            } else {
                mutableIsConnectedToInternet.value = false
                val url = sharedPreferences.getString(NasaApplication.URL, "")
                if (!TextUtils.isEmpty(url)) {
                    val title = sharedPreferences.getString(NasaApplication.TITLE, "")
                    val description = sharedPreferences.getString(NasaApplication.DESCRIPTION, "")
                    nasaInfo.value = (NasaInfo(url!!, title!!, description!!))
                }
            }
        }

    }

    suspend fun isConnectedToInternet() : Boolean{
        val result: Boolean =  withContext(Dispatchers.IO) {
            try {
                val address: InetAddress = InetAddress.getByName("www.google.com")
                !address.equals("")
            } catch (e: UnknownHostException) {
                // Log error
                false
            }
        }
        return result
    }


    fun observeNasaData(): LiveData<NasaInfo> {
        return nasaInfo
    }

    fun observeInternetConnection(): LiveData<Boolean> = mutableIsConnectedToInternet
}