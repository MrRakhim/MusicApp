package com.example.music.Activities

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music.Adapter.AlbumAdapter
import com.example.music.Adapter.ArtistAdapter
import com.example.music.AppDatabase
import com.example.music.Loaders.AlbumLoader
import com.example.music.Loaders.ArtistLoader
import com.example.music.Prefs.ApiFactory
import com.example.music.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_artist.*
import kotlinx.android.synthetic.main.layout_artist.view.*


class SearchActivity : AppCompatActivity() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupView()


    }

    private fun setupView(){

        nav_search_button.setOnClickListener {
        }
        nav_search_button.setImageResource(R.drawable.ic_search_pink)

        nav_album_button.setOnClickListener {
            startActivity(Intent(this@SearchActivity, MainActivity::class.java))
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchItem = menu?.findItem(R.id.search_bar)
        if (searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    artists_search_list.layoutManager = LinearLayoutManager(this@SearchActivity)
                    if (newText != null) {
                        ArtistLoader(onSuccess = { artistList ->
                            artists_search_list.adapter = ArtistAdapter((artistList),{ artist ->
                                startActivity(Intent(this@SearchActivity, ArtistActivity::class.java).apply {
                                    putStringArrayListExtra(ArtistActivity.ARTIST, artist)
                                })
                            },
                                {likedArtist->
                                    if(auth.currentUser != null){
                                        AsyncTask.execute {
                                            val userId = ApiFactory.auth.currentUser!!.uid
                                            val artistList =  AppDatabase.getInstance(applicationContext)!!
                                                .getItemDao().getLikedArtist(userId, likedArtist[0])
                                            runOnUiThread {
                                                if (artistList.isNotEmpty()) {
                                                    artist_like_button.setImageResource(R.drawable.ic_checked)
                                                }else{
                                                    val artist_id = likedArtist[0]
                                                    val artist_name = likedArtist[1]
                                                    val artist_img = likedArtist[2]
                                                    val liked = UserArtist(user_id = userId, artist_id = artist_id, artist_name = artist_name, artist_img = artist_img)
                                                    AsyncTask.execute {
                                                        AppDatabase.getInstance(applicationContext)!!
                                                            .getItemDao().insertLikedArtist(liked)
                                                        runOnUiThread{
                                                            artist_like_button.setImageResource(R.drawable.ic_checked)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }else{
                                        var toast = Toast.makeText(this@SearchActivity, "You must be logged in!", Toast.LENGTH_LONG)
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show()

                                    }
                                }
                            )}, onError = { Log.d("artistError", it) }).loadArtist(newText)
                    albums_search_list.layoutManager = LinearLayoutManager(this@SearchActivity)
                        AlbumLoader(onSuccess = {
                            albums_search_list.adapter = AlbumAdapter(it) { album ->
                                startActivity(Intent(this@SearchActivity, AlbumActivity::class.java).apply {
                                    putStringArrayListExtra(AlbumActivity.SONG, album)
                                })
                            }
                        }, onError = { Log.d("albumError", it) }).loadAlbum(newText)
                    }
                    Log.d("artist", newText)
//
                    return true
                }

            })
        }
        return true
    }

}
