package com.example.music.Loaders
import android.util.Log
import com.example.music.Objects.Album
import com.example.music.Objects.AlbumList
import com.example.music.Prefs.ApiFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumLoader (
    private val onSuccess: (List<Album>) -> Unit,
    private val onError: (String) -> Unit
){
    fun loadAlbum(search: String) {
        ApiFactory.getApi()
            .getAlbum(search)
            .enqueue(object : Callback<AlbumList> {
                override fun onResponse(
                    call: Call<AlbumList>,
                    response: Response<AlbumList>
                ){

                    Log.d("allbuuuum", response.body()?.toString())
                    response.body()?.album?.let {
                        onSuccess(it)
                    }
                }
                override fun onFailure(call: Call<AlbumList>, t: Throwable) {
                    onError(t.message!!)
                }
            })

    }
}