package com.example.music
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistLoader (
    private val onSuccess: (List<Artist>) -> Unit,
    private val onError: (Throwable) -> Unit
){
    fun loadArtist(search: String) {
        ApiFactory.getApiArtist().getArtist(search).enqueue(object : Callback<List<Artist>> {
            override fun onResponse(call: Call<List<Artist>>, response: Response<List<Artist>>) {
                onSuccess(response.body()!!)
            }
            override fun onFailure(call: Call<List<Artist>>, t: Throwable) {
                onError(t)
            }
        })
    }
}