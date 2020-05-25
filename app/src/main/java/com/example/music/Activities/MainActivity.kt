package com.example.music.Activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.music.Adapter.LikedArtistAdapter
import com.example.music.AppDatabase
import com.example.music.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }
    fun hello(){

    }
    private fun setupView(){
        nav_album_button.setImageResource(R.drawable.ic_library_music_pink)

        nav_search_button.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }
        if(auth.currentUser != null){

            AsyncTask.execute {
                val userId = auth.currentUser!!.uid
                val artistList =  AppDatabase.getInstance(applicationContext)!!
                    .getItemDao().getLikedArtists(userId)
                val title = AppDatabase.getInstance(applicationContext)!!
                    .getItemDao().getUser(userId).get(0).name
                setTitle(title)
                runOnUiThread {
                    liked_artist_list.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    liked_artist_list.adapter = LikedArtistAdapter(artistList){id->
                        val goArtist = Intent(this@MainActivity, ArtistActivity::class.java)
                        goArtist.putExtra(ArtistActivity.ARTIST_FROM_DB, id)
                        startActivity(goArtist)
                    }

                }
            }
        }else{
            Toast.makeText(this, "You must be logged in!", Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if(auth?.currentUser != null){
            menuInflater.inflate(R.menu.menu, menu)
            menu?.removeItem(R.id.login_button)
            menu?.removeItem(R.id.register_button)

        }else{
            menuInflater.inflate(R.menu.menu, menu)
            menu?.removeItem(R.id.account_button)
            menu?.removeItem(R.id.logout_button)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item?.itemId){
            R.id.logout_button ->{
                auth.signOut()
                var refresh =  Intent(this, MainActivity::class.java)
                startActivity(refresh)
                true
            }
            R.id.login_button -> {
                var login =  Intent(this, LoginActivity::class.java)
                startActivity(login)
                true
            }
            R.id.register_button -> {
                var login =  Intent(this, RegisterActivity::class.java)
                startActivity(login)
                true
            }
            R.id.account_button ->{
                var toast = Toast.makeText(
                    this,
                    "Account",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show()
                Log.d("option", "Account")
                true
            }
            else-> {
                return super.onOptionsItemSelected(item)
            }
        }
    }


}
