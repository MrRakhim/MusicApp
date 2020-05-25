package com.example.music.Loaders

import com.example.music.Prefs.ApiFactory
import com.example.music.Objects.Song
import com.example.music.Objects.SongList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongLoader (
    private val onSuccess: (List<Song>) -> Unit,
    private val onError: (String) -> Unit
){
    fun loadSong(albumId: String){
        ApiFactory.getApi()
            .getSong(albumId)
            .enqueue(object : Callback<SongList> {
                override fun onResponse(
                    call: Call<SongList>,
                    response: Response<SongList>
                ){
                    response.body()?.track?.let {
                        onSuccess(it)
                    }
                }
                override fun onFailure(call: Call<SongList>, t: Throwable) {
                    onError(t.message!!)
                }
            })
    }
}