package com.example.music.Objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    @SerializedName("strAlbum") val name: String,
    @SerializedName("strArtist") val artist: String,
    @SerializedName("intYearReleased") val date: String,
    @SerializedName("strGenre") val genre: String,
    @SerializedName("strDescriptionEN") var description: String?,
    @SerializedName("idAlbum") val id: String,
    @SerializedName("strAlbumThumb") val image: String?
) : Parcelable

data class AlbumList(
    val album: List<Album>
)