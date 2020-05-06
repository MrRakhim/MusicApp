package com.example.music

import android.util.Log
import android.widget.ImageButton
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.text.FieldPosition

object changeTabs{
    lateinit var  mViewPager: ViewPager
    lateinit var albumBtn: ImageButton
    lateinit var songBtn: ImageButton
    lateinit var artistBtn: ImageButton
    lateinit var searchBtn: ImageButton
    lateinit var pagerAdapter: PagerAdapter
    fun hello(){
        Log.d("taag", "hello")
    }
    fun main(){
        mViewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                changingTabs(position)
            }
        })
    }

    fun changingTabs(position: Int){

        if (position==0){
            albumBtn.setImageResource(R.drawable.ic_library_music_pink)
            songBtn.setImageResource(R.drawable.ic_music_note_black_24dp)
            artistBtn.setImageResource(R.drawable.ic_account_box_black)
            searchBtn.setImageResource(R.drawable.ic_search_black_24dp)
        }
        if (position==1){
            albumBtn.setImageResource(R.drawable.ic_library_music_black_24dp)
            songBtn.setImageResource(R.drawable.ic_music_note_pink)
            artistBtn.setImageResource(R.drawable.ic_account_box_black)
            searchBtn.setImageResource(R.drawable.ic_search_black_24dp)
        }
        if (position==2){
            albumBtn.setImageResource(R.drawable.ic_library_music_black_24dp)
            songBtn.setImageResource(R.drawable.ic_music_note_black_24dp)
            artistBtn.setImageResource(R.drawable.ic_account_box_pink)
            searchBtn.setImageResource(R.drawable.ic_search_black_24dp)
        }
        if (position==3){
            albumBtn.setImageResource(R.drawable.ic_library_music_black_24dp)
            songBtn.setImageResource(R.drawable.ic_music_note_black_24dp)
            artistBtn.setImageResource(R.drawable.ic_account_box_black)
            searchBtn.setImageResource(R.drawable.ic_search_pink)

        }

    }
}