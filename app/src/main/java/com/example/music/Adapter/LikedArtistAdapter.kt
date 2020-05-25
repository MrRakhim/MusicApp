package com.example.music.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music.Activities.UserArtist
import com.example.music.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_likedartist.view.*

class LikedArtistAdapter(
    private val artists: List<UserArtist>,
    private val onItemClicked: (String) -> Unit
): RecyclerView.Adapter<LikedArtistAdapter.LikedArtistViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LikedArtistAdapter.LikedArtistViewHolder =
        LikedArtistViewHolder(
            LayoutInflater.from(parent.context!!)
                .inflate(R.layout.layout_likedartist, parent, false)
        )


    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: LikedArtistAdapter.LikedArtistViewHolder, position: Int) {
        holder.bindLikedArtist(artists[position])
    }
    inner class LikedArtistViewHolder(private val view: View
    ): RecyclerView.ViewHolder(view){
        fun bindLikedArtist(artist: UserArtist){
            Picasso.with(itemView.context)
                .load(artist.artist_img)
                .into(view.liked_artist_img)
            view.liked_artist_name.text = artist.artist_name
            view.setOnClickListener {
                onItemClicked(artist.artist_id)
            }
        }
    }
}