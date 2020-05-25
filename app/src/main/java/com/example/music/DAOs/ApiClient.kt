package com.example.music.DAOs


import com.example.music.Objects.AlbumList
import com.example.music.Objects.ArtistList
import com.example.music.Objects.SongList
import com.example.music.Objects.VideoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    //https://www.theaudiodb.com/api/v1/json/1/searchalbum.php?s=Eminem
    @GET("search.php")
    fun getArtist(@Query("s") s:String):Call<ArtistList>

    @GET("artist.php")
    fun getLikedArtist(@Query("i") i:String):Call<ArtistList>

    @GET("searchalbum.php")
    fun getAlbum(@Query("s") s:String):Call<AlbumList>

    @GET("track.php")
    fun getSong(@Query("m") m:String):Call<SongList>

    @GET("mvid.php")
    fun getVideo(@Query("i") i:String):Call<VideoList>


}
