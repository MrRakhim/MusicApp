package com.example.music

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.get
import com.example.music.Adapter.PageAdapter
import com.example.music.changeTabs.changingTabs
import com.example.music.changeTabs.main
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_song.*

class MainActivity : AppCompatActivity() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //signIn("test1@gmail.com", "qwerty")
        //Log.d("user", auth.currentUser?.uid)

        initViews()

    }


    private fun initViews(){
        changeTabs.mViewPager = findViewById(R.id.mViewPager)
        changeTabs.albumBtn = findViewById(R.id.nav_album_button)
        changeTabs.songBtn = findViewById(R.id.nav_song_button)
        changeTabs.artistBtn = findViewById(R.id.nav_artist_button)
        changeTabs.searchBtn = findViewById(R.id.nav_search_button)
        changeTabs.pagerAdapter = PageAdapter(supportFragmentManager)
        changeTabs.mViewPager.adapter = changeTabs.pagerAdapter
        changeTabs.mViewPager.offscreenPageLimit = 4
        main()
        nav_search_button.setOnClickListener {
            changeTabs.mViewPager.currentItem = 3
            changingTabs(3)
        }
        nav_artist_button.setOnClickListener {
            changeTabs.mViewPager.currentItem = 2
            changingTabs(2)
        }
        nav_song_button.setOnClickListener {
            changeTabs.mViewPager.currentItem = 1
            changingTabs(1)
        }
        nav_album_button.setOnClickListener {
            changeTabs.mViewPager.currentItem = 0
            changingTabs(0)
        }
        changeTabs.mViewPager.currentItem = 0
        changeTabs.albumBtn.setImageResource(R.drawable.ic_library_music_pink)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(auth?.currentUser != null){
            menuInflater.inflate(R.menu.menulogged, menu)
        }else{
            menuInflater.inflate(R.menu.menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            R.id.logout_button->{
                auth.signOut()
                initViews()
                true
            }
            R.id.login_button->{
                changeTabs.mViewPager.display
                signIn("test1@gmail.com", "qwerty")
                Log.d("option", "${auth.currentUser?.uid}")
                initViews()
                true
            }
            R.id.register_button->{
                Toast.makeText(this, "Register", Toast.LENGTH_LONG)

                true
            }
            R.id.account_button->{
                Toast.makeText(changeTabs.mViewPager.context, "Account", Toast.LENGTH_LONG)
                Log.d("option", "Account")
                true
            }
            else->{
                return super.onOptionsItemSelected(item)

            }
        }
    }

    private fun signUp(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
    }
    private fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
    }
}
