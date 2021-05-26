package com.nasa.wmart.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.nasa.wmart.data.source.NasaApiDataSource
import com.nasa.wmart.model.NasaInfo

class NasaApiRemoteDataSource: NasaApiDataSource {

    override fun fetchNasaApodData(): MutableLiveData<NasaInfo>? {
        return null
    }
}