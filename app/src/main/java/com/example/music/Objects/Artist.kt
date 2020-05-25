package com.example.music.Objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist (
    @SerializedName("strArtist") val name: String,
    @SerializedName("intBornYear") val born_year: String?,
    @SerializedName("strGenre") val genre: String?,
    @SerializedName("strWebsite") val website: String?,
    @SerializedName("strBiographyEN") val biography: String?,
    @SerializedName("strArtistThumb") val image_thumb: String?,
    @SerializedName("idArtist") val id: String
) : Parcelable

data class ArtistList (
    val artists: List<Artist>
)