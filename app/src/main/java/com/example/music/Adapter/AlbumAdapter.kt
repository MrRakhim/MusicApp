package com.example.music.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music.Objects.Album
import com.example.music.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_album.view.*

class AlbumAdapter(
    private val albums: List<Album>,
    private val onItemClicked: (ArrayList<String>) -> Unit
): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.AlbumViewHolder =
        AlbumViewHolder(
            LayoutInflater.from(parent.context!!)
                .inflate(R.layout.layout_album, parent, false)
        )

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bindAlbum(albums[position])
    }

    inner class AlbumViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view) {
        fun bindAlbum(album: Album){

           if(album.image != null && album.image?.length!!>0) {
               Picasso.with(itemView.context)
                   .load(album.image)
                   .into(view.album_image_little)
           }else{
               Picasso.with(itemView.context)
                   .load(R.drawable.ic_account_box_pink)
                   .into(view.album_image_little)
           }
            view.album_name_textview.text = album.name
            view.album_artist_textview.text = album.artist
            view.album_year_textview.text = album.date

            var desc = album.description
            if(album.description == null){
                desc = "Information about "+album.name+" still being replenished"
            }
            val albumgoo: MutableList<String> = ArrayList(7)
            albumgoo.add(album.name)
            albumgoo.add(album.artist)
            albumgoo.add(album.date)
            if (desc != null) {
                albumgoo.add(desc)
            }
            albumgoo.add(album.genre)
            albumgoo.add(album.id)
            album.image?.let { albumgoo.add(it) }

            view.setOnClickListener {
                onItemClicked(albumgoo as ArrayList<String>)
            }

        }
    }
}