package com.example.music.Loaders
import android.util.Log
import com.example.music.Prefs.ApiFactory
import com.example.music.Objects.Artist
import com.example.music.Objects.ArtistList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistLoader (
    private val onSuccess: (List<Artist>) -> Unit,
    private val onError: (String) -> Unit
){
    fun loadArtist(search: String) {
        ApiFactory.getApi()
            .getArtist(search)
            .enqueue(object : Callback<ArtistList>{
                override fun onResponse(
                    call: Call<ArtistList>,
                    response: Response<ArtistList>
                ){
                    response.body()?.artists?.let {
                        onSuccess(it)

                    }
                }
                override fun onFailure(call: Call<ArtistList>, t: Throwable) {
                    onError(t.message!!)
                }
            })

    }
    fun loadLikedArtist(id: String){
        ApiFactory.getApi()
            .getLikedArtist(id)
            .enqueue(object : Callback<ArtistList>{
                override fun onResponse(
                    call: Call<ArtistList>,
                    response: Response<ArtistList>
                ){
                    Log.d("arrrtist", response.toString())
                    response.body()?.artists?.let {
                        onSuccess(it)
                    }
                }
                override fun onFailure(call: Call<ArtistList>, t: Throwable) {
                    onError(t.message!!)
                }
            })
    }
}


