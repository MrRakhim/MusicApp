package com.example.music.Adapter

import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music.Activities.SearchActivity
import com.example.music.Activities.UserArtist
import com.example.music.AppDatabase
import com.example.music.Objects.Artist
import com.example.music.Prefs.ApiFactory
import com.example.music.R
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_album.view.*
import kotlinx.android.synthetic.main.layout_artist.view.*

class ArtistAdapter(
    private val artists: List<Artist>,
    private val onItemClicked: (ArrayList<String>) -> Unit,
    private val onLikeClicked: (ArrayList<String>) -> Unit
): RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ArtistViewHolder =
        ArtistViewHolder(
            LayoutInflater.from(parent.context!!)
                .inflate(R.layout.layout_artist, parent, false)

        )


    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bindArtist(artists[position])
    }
    inner class ArtistViewHolder (
        private val view: View
    ): RecyclerView.ViewHolder(view){

        fun bindArtist(artist: Artist){
            if(artist.image_thumb?.length!!>0 && artist.image_thumb != null) {
                Picasso.with(itemView.context)
                    .load(artist.image_thumb)
                    .into(view.artist_image_little)
            }else{
                Picasso.with(itemView.context)
                    .load(R.drawable.ic_account_box_pink)
                    .into(view.artist_image_little)
            }

            view.artist_name_little.text = artist.name
            val artistko: MutableList<String> = ArrayList(7)
            artistko.add(artist.name)
            artist.image_thumb?.let { artistko.add(it) }
            artist.biography?.let { artistko.add(it) }
            artist.born_year?.let { artistko.add(it) }
            artist.genre?.let { artistko.add(it) }
            artist.website?.let { artistko.add(it) }
            artistko.add(artist.id)

            val liked: MutableList<String> = ArrayList(3)
            liked.add(artist.id)
            liked.add(artist.name)
            artist.image_thumb?.let { liked.add(it) }

                if(auth.currentUser != null){
                AsyncTask.execute {
                    val userId = ApiFactory.auth.currentUser!!.uid
                    val artistList =  AppDatabase.getInstance(view.context)!!
                        .getItemDao().getLikedArtist(userId,artist.id)
                    run {
                        if (artistList.isNotEmpty()) {
                            view.artist_like_button.setImageResource(R.drawable.ic_checked)
                        }
                    }
                }
            }

            view.artist_like_button.setOnClickListener {
                onLikeClicked(liked as ArrayList<String>)
            }
            view.setOnClickListener {
                onItemClicked(artistko as ArrayList<String>)
            }
        }

    }
}