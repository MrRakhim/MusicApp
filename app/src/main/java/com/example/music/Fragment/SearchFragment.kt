package com.example.music.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.music.Adapter.ArtistAdapter
import com.example.music.ArtistLoader

import com.example.music.R
import com.example.music.changeTabs
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {
    var artistList:MutableList<String> = ArrayList()
    var displayList:MutableList<String> = ArrayList()
    companion object{
        const val SEARCH_TEXT = "newtext"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    fun search(text:String){
        changeTabs.mViewPager.currentItem = 3
        if (text!!.isNotEmpty()){
            displayList.clear()
            val search = text.toLowerCase()
            artistList.forEach {
                if (it.toLowerCase().contains(search)) displayList.add(it)
            }
            Log.d("Search", "searching...")
            ArtistLoader(onSuccess = {
                list_view.adapter = ArtistAdapter(it)

                { artist ->
                    startActivity(Intent(activity, ArtistFragment::class.java).apply {
                        putExtra(ArtistFragment.ARTIST, artist)
                    })
                }
            }, onError = {
                    Toast.makeText(activity, "there is no such artist!", Toast.LENGTH_LONG).show()
                }).loadArtist(text)
        }else{
            displayList.clear()
            displayList.addAll(artistList)
            ArtistLoader(onSuccess = {
                list_view.adapter = ArtistAdapter(it)

                { artist ->
                    startActivity(Intent(activity, ArtistFragment::class.java).apply {
                        putExtra(ArtistFragment.ARTIST, artist)
                    })
                }
            }, onError = {
                    Toast.makeText(activity, "there is no such artist!", Toast.LENGTH_LONG).show()
                    Log.d("Search", it.toString())
                }).loadArtist(text)

        }

    }

}
