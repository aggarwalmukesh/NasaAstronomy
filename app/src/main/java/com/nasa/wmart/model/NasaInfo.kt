package com.nasa.wmart.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "nasa")
data class NasaInfo(
    @ColumnInfo val url: String,
    @ColumnInfo val title: String,
    @ColumnInfo val explanation: String
)