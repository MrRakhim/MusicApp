package com.example.music

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val ARTISTENDPOINT = "https://theaudiodb.com/api/v1/json/1/"

    fun getArtistRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(ARTISTENDPOINT).addConverterFactory(GsonConverterFactory.create()).build()

    fun getApiArtist(): ApiClient = getArtistRetrofit().create(ApiClient::class.java)
}
