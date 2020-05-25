package com.example.music.Activities

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music.Adapter.VideoAdapter
import com.example.music.AppDatabase
import com.example.music.Loaders.ArtistLoader
import com.example.music.Loaders.VideoLoader
import com.example.music.Objects.Artist
import com.example.music.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist.*


class ArtistActivity : AppCompatActivity() {
    companion object{
        const val ARTIST = "artist"
        const val ARTIST_FROM_DB = "ArtistIdOnly"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
        setupView()

    }
    private fun setupView(){
        nav_search_button.setOnClickListener {
            startActivity(Intent(this@ArtistActivity, SearchActivity::class.java))
        }
        nav_album_button.setOnClickListener {
            startActivity(Intent(this@ArtistActivity, MainActivity::class.java))
        }
        val artist = intent.getStringArrayListExtra(ARTIST)
        val artistDB = intent.getStringExtra(ARTIST_FROM_DB)
        var loadInfo = false
        var currentArtist = Artist("","","","","","","")
        if(artist != null){
            val name = artist[0]
            val image = artist[1]
            val description = artist[2]
            val date = artist[3]
            val genre = artist[4]
            val website = artist[5]
            val id = artist[6]
            currentArtist = Artist(name, date,genre, website, description, image, id)
            loadInfo(currentArtist, true)
            Log.d("hhhhhhhh1",currentArtist.toString())

        }else if(artistDB != null){
            ArtistLoader(onSuccess = {artistList->
                currentArtist = artistList[0]
                loadInfo(currentArtist, true)
            }, onError = {
                Log.d("error", it)
            }).loadLikedArtist(artistDB)
        }
        Log.d("hhhhhhhhlast",currentArtist.toString())

    }
    private fun loadInfo(currentArtist: Artist, loadInfo: Boolean){
        if(loadInfo){
            Log.d("hhhhhhhh4",currentArtist.name.toString())
            Picasso.with(this)
                .load(currentArtist.image_thumb)
                .into(artist_image)
            artist_name.text = currentArtist.name
            if(currentArtist.biography?.isEmpty()!! || currentArtist.biography == null){
                artist_about.setText("Information about ${currentArtist.name} still being replenished")
            }else {
                artist_about.setText(currentArtist.biography)
            }
            artist_birthdate.text = "Was born in ${currentArtist.born_year}"
            artist_genre.text = "Genre: ${currentArtist.genre}"
            artist_website.text = Html.fromHtml("<a href=\"https://${currentArtist.website}\">${currentArtist.website}</a>");

            artist_website.setOnClickListener {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://${currentArtist.website}"))
                startActivity(browserIntent)
            }

            artistAC_video_list.layoutManager = LinearLayoutManager(this)
            VideoLoader(onSuccess = {
                artistAC_video_list.adapter = VideoAdapter(it) { url ->
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(browserIntent)
                }
            }, onError = { Log.d("video", it) }).loadVideo(currentArtist.id)
        }
    }

}
