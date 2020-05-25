package com.example.music.Prefs

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.example.music.Activities.SearchActivity
import com.example.music.AppDatabase
import com.example.music.DAOs.ApiClient
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val ARTISTENDPOINT = "https://www.theaudiodb.com/api/v1/json/1/"
    val auth by lazy { FirebaseAuth.getInstance() }
    fun getRetrofit() =
        Retrofit.Builder()
            .baseUrl(ARTISTENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getApi() =
        getRetrofit().create(ApiClient::class.java)


}

