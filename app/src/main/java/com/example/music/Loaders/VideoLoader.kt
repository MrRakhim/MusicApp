package com.example.music.Loaders

import com.example.music.Prefs.ApiFactory
import com.example.music.Objects.Video
import com.example.music.Objects.VideoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoLoader (
    private val onSuccess: (List<Video>) -> Unit,
    private val onError: (String) -> Unit
){
    fun loadVideo(artistId: String){
        ApiFactory.getApi()
            .getVideo(artistId)
            .enqueue(object : Callback<VideoList> {
                override fun onResponse(
                    call: Call<VideoList>,
                    response: Response<VideoList>
                ){
                    response.body()?.mvids?.let {
                        onSuccess(it)
                    }
                }
                override fun onFailure(call: Call<VideoList>, t: Throwable) {
                    onError(t.message!!)
                }
            })
    }

}