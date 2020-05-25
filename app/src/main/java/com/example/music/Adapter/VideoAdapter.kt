package com.example.music.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.Objects.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_video.view.*

class VideoAdapter (
    private val videos: List<Video>,
    private val onItemClicked: (String) -> Unit
):RecyclerView.Adapter<VideoAdapter.VideoHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.VideoHolder =
        VideoHolder(
            LayoutInflater.from(parent.context!!)
                .inflate(R.layout.layout_video, parent, false)
        )

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: VideoAdapter.VideoHolder, position: Int) {
        holder.bindVideo(videos[position])
    }
    inner class VideoHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view){
        fun bindVideo(video: Video) {
            val url = video.link
            val id = url.split("=").toTypedArray()
            val last = id[id.size-1]
            val imgUrl = "https://img.youtube.com/vi/$last/0.jpg"
            Picasso.with(itemView.context)
                .load(imgUrl)
                .into(view.video_image_little)

            view.video_name_textview.text = video.name
            view.setOnClickListener {
                onItemClicked(video.link)
            }
        }
    }
}