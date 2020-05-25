package com.example.music

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.music.Activities.UserArtist
import com.example.music.DAOs.AppDAO
import com.example.music.Objects.Artist
import com.example.music.Objects.User

@Database(entities = [UserArtist::class, User::class],version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getItemDao(): AppDAO

    companion object{

        private const val DB_NAME = "artists.db"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if(instance ==null){
                instance = Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
            }
            return instance
        }
    }
}