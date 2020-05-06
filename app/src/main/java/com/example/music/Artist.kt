package com.example.music

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Artist (
    @SerializedName("strArtist") val name: String,
    @SerializedName("intBornYear") val born_year: Int,
    @SerializedName("strGenre") val genre: String,
    @SerializedName("strWebsite") val website: String,
    @SerializedName("strBiographyEN") val biography: String,
    @SerializedName("strArtistThumb") val image_thumb: String
):Serializable