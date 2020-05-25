package com.example.music.Activities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artistter")
data class UserArtist(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "user_id")val user_id: String,
    @ColumnInfo(name = "artist_id")val artist_id: String,
    @ColumnInfo(name = "artist_name")val artist_name: String,
    @ColumnInfo(name = "artist_img")val artist_img: String
)