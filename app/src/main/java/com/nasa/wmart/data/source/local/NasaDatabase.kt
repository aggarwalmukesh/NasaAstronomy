package com.nasa.wmart.data.source.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nasa.wmart.model.NasaInfo

@Database(entities = arrayOf(NasaInfo::class), version = 1)
public abstract class NasaDatabase : RoomDatabase() {
    abstract fun nasaDao(): NasaDao

    companion object {
        var INSTANCE: NasaDatabase? = null

        fun getAppDataBase(context: Application): NasaDatabase? {
            if (INSTANCE == null) {
                synchronized(NasaDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NasaDatabase::class.java, "nasadb"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}