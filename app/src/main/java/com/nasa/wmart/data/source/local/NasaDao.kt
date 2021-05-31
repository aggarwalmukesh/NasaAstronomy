package com.nasa.wmart.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nasa.wmart.model.NasaInfo

@Dao
interface NasaDao {

    @Query("SELECT * FROM nasa")
    fun getNasaInfo(): NasaInfo

    @Insert
    fun insertNasaInfo(nasaInfo: NasaInfo)

    @Delete
    fun delete()
}