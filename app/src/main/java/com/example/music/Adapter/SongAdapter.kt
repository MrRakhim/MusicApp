package com.example.music.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.Objects.Song
import kotlinx.android.synthetic.main.layout_song.view.*

class SongAdapter (
    private val songs: List<Song>,
    private val onItemClicked: (ArrayList<String>) -> Unit
): RecyclerView.Adapter<SongAdapter.SongViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter.SongViewHolder =
        SongViewHolder(
            LayoutInflater.from(parent.context!!)
                .inflate(R.layout.layout_song, parent, false)
        )

    override fun getItemCount(): Int  = songs.size

    override fun onBindViewHolder(holder: SongAdapter.SongViewHolder, position: Int) {
        holder.bindSong(songs[position])
    }
    inner class SongViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view){
        fun bindSong(song: Song){
            val trackkoo: MutableList<String> = ArrayList(4)
            view.song_number_little.text = song.number.toString()
            view.song_name_little.text = song.name
            trackkoo.add(song.artist)
            trackkoo.add(song.name)
            trackkoo.add(song.duration.toString())
            trackkoo.add(song.number.toString())

            view.setOnClickListener {
                onItemClicked(trackkoo as ArrayList<String>)
            }
        }
    }

}