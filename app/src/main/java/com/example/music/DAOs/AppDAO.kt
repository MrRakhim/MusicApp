package com.example.music.DAOs


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.music.Activities.UserArtist
import com.example.music.Objects.User

@Dao
interface AppDAO {

    @Insert
    fun insertUser(user:User)

    @Insert
    fun insertLikedArtist(likedArtist:UserArtist)

    @Query("Select * from users")
    fun getUsers(): List<User>

    @Query("Select * from users where user_id =:user_id")
    fun getUser(user_id: String?): List<User>

    @Query("Select * from artistter where user_id = :user_id")
    fun getLikedArtists(user_id: String?): List<UserArtist>

    @Query("Select * from artistter where user_id = :user_id and artist_id= :artist_id")
    fun getLikedArtist(user_id: String?, artist_id: String?): List<UserArtist>

}

