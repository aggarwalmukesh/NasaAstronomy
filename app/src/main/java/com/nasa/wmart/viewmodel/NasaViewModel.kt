package com.nasa.wmart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nasa.wmart.data.source.NasaApiDataSource
import com.nasa.wmart.model.NasaInfo

class NasaViewModel(private val dataSource: NasaApiDataSource) : ViewModel() {
    private lateinit var nasaInfo: MutableLiveData<NasaInfo>

    fun fetchNasaImageData(): LiveData<NasaInfo>? {
        return dataSource.fetchNasaApodData()
    }
}