package com.example.music.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music.Artist
import com.example.music.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_artist.view.*
import kotlinx.android.synthetic.main.layout_item.view.*

class ArtistAdapter(
    private val artists: List<Artist>,
    private val onItemClicked: (ArrayList<String>) -> Unit
): RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ArtistViewHolder(
        LayoutInflater.from(parent.context)
        .inflate(R.layout.layout_item, parent, false))


    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bindArtist(artists[position])
    }
    inner class ArtistViewHolder (
        private val view: View
    ): RecyclerView.ViewHolder(view){

        fun bindArtist(artist: Artist){
            Picasso.with(itemView.context)
                .load(artist.image_thumb)
                .into(view.artist_image_little)

            view.artist_name_little.text = artist.name
            val artistko: MutableList<String> = ArrayList<String>(6)
            artistko.add(artist.name)
            artistko.add(artist.image_thumb)
            artistko.add(artist.biography)
            artistko.add(artist.born_year.toString())
            artistko.add(artist.genre)
            artistko.add(artist.website)
            view.setOnClickListener {
                onItemClicked(artistko as ArrayList<String>)
            }
        }

    }
}