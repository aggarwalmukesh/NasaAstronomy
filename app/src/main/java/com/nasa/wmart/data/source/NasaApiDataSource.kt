package com.nasa.wmart.data.source

import androidx.lifecycle.MutableLiveData
import com.nasa.wmart.model.NasaInfo

interface NasaApiDataSource {

    fun fetchNasaApodData(): MutableLiveData<NasaInfo>?
}