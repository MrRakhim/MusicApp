package com.example.music.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.music.Fragment.AlbumFragment
import com.example.music.Fragment.ArtistFragment
import com.example.music.Fragment.SearchFragment
import com.example.music.Fragment.SongFragment

internal class PageAdapter(fm:FragmentManager?): FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                AlbumFragment()
            }
            1->{
                SongFragment()
            }
            2->{
                ArtistFragment()
            }
            3->{
                SearchFragment()
            }
            else ->{
                AlbumFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }
}