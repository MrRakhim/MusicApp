package com.example.music.Objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    @SerializedName("strTrack") val name: String,
    @SerializedName("strMusicVid") val link: String
) : Parcelable

data class VideoList(
    val mvids: List<Video>
)