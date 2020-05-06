package com.example.music


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("search.php")
    fun getArtist(@Query("s") s:String):Call<List<Artist>>

//    @GET("searchalbum.php")
//    fun getAlbumbyName(@Query("s") s: String):Call<AlbumResponse>
//
//    @GET("track-top10.php")
//    fun getArtistTopTrack(@Query("s") s: String):Call<TrackResponse>
//
//    @GET("track.php")
//    fun getAlbumTrack(@Query("m") m: Int):Call<AlbumTrackResponse>
//
//    @GET("mvid.php")
//    fun getVideoByArtist(@Query("i") m: Int): Call<VideoResponse>
}

annotation class Query(val value: String)
