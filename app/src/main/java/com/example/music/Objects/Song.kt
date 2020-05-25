package com.example.music.Objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song (
    @SerializedName("strArtist") val artist: String,
    @SerializedName("strTrack") val name: String,
    @SerializedName("intDuration") val duration: Int,
    @SerializedName("intTrackNumber") val number: Int
) : Parcelable

data class SongList (
    val track: List<Song>
)