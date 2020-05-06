package com.example.music

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.music.Adapter.PageAdapter
import com.example.music.Fragment.SearchFragment
import com.example.music.changeTabs.changingTabs
import com.example.music.changeTabs.main
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //signIn("test1@gmail.com", "qwerty")
        //Log.d("user", auth.currentUser?.uid)
        initViews()

    }
    private fun setupView(){

    }
    private fun signUp(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
    }
    private fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.search_bar)
            if (searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText != null) {
                        SearchFragment().search(newText)


                    }
                    return true
                }

            })
        }
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
            R.id.logout_button->{
                auth.signOut()
                var refresh =  Intent(this, MainActivity::class.java)
                startActivity(refresh)
                true
            }
            R.id.login_button-> {
                signIn("test1@gmail.com", "qwerty")

                var refresh =  Intent(this, MainActivity::class.java)
                startActivity(refresh)
                true
            }
            R.id.register_button-> {
                var toast = Toast.makeText(
                    this,
                    "Register",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show()
                true
            }
            R.id.account_button->{
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
