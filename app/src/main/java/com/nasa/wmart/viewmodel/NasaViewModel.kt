package com.nasa.wmart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nasa.wmart.data.source.remote.NasaApiRemoteDataSource
import com.nasa.wmart.model.NasaInfo

class NasaViewModel(private val dataSource: NasaApiRemoteDataSource = NasaApiRemoteDataSource()) : ViewModel() {
    private var nasaInfo: MutableLiveData<NasaInfo> = dataSource.getNasaInfoData()

    fun fetchNasaImageData() {
        dataSource.fetchNasaApodData()
    }

    fun observeNasaData() : LiveData<NasaInfo> {
        return nasaInfo
    }
}