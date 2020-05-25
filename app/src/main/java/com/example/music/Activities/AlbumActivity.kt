package com.example.music.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music.Adapter.SongAdapter
import com.example.music.Loaders.SongLoader
import com.example.music.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album.*

class AlbumActivity : AppCompatActivity() {
    companion object{
        const val SONG = "911"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        setupView()
    }

    private fun setupView(){
        val album = intent.getStringArrayListExtra(SONG)
        if (album.size>0){
            val name = album[0]
            val artist = album[1]
            val date = album[2]
            var description = album[3]
            val genre = album[4]
            val id = album[5]
            val image = album[6]
            Picasso.with(this)
                .load(image)
                .into(albumAC_image)
            albumAC_name_textview.text = name
            albumAC_artist_textview.text = artist
            albumAC_genre_textview.text = genre
            albumAC_year_textview.text = "• $date"
            albumAC_desc_textview.setText(description)



            albumAC_songs_list.layoutManager = LinearLayoutManager(this)
            SongLoader(onSuccess = {
                albumAC_songs_list.adapter = SongAdapter(it) { track ->
                    startActivity(Intent(this@AlbumActivity, SongActivity::class.java).apply {
                        putStringArrayListExtra(SongActivity.SONG, track)
                        putExtra(SongActivity.IMAGE, image)
                    })
                    Log.d("trackkkkko", track.toString())
                }
            }, onError = { Log.d("albumError", it) }).loadSong(id)


        }
    }
}
