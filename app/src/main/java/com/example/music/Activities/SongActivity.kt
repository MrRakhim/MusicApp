package com.example.music.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.music.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_song.*

class SongActivity : AppCompatActivity() {
    companion object{
        const val SONG = "song"
        const val IMAGE = "songImage"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        setupView()
    }
    private fun setupView(){
        val song = intent.getStringArrayListExtra(SONG)
        val image = intent.getStringExtra(IMAGE)
        Picasso.with(this)
            .load(image)
            .into(songAC_image)
        val name = song[1]
        val artist = song[0]
        val temp = song[2].toInt()

        val min = (temp/1000/60)
        val sec = (temp/1000%60)
        val duration = "-$min:$sec"
        Log.d("duratioooon", duration)
        songAC_name_textview.text = name
        songAC_artist_textview.text = artist
        songAC_duration_textview.text = duration
        var cnt = 1
        songAC_play_button.setOnClickListener {
            cnt ++
            if(cnt%2 == 0){
                songAC_play_button.setImageResource(R.drawable.ic_pause_black_24dp)
            }else{
                songAC_play_button.setImageResource(R.drawable.ic_play_black_24dp)
            }
        }

    }
}
