package com.rrat.hackernews.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rrat.hackernews.model.data.entities.Post

@Database(entities = [Post::class], version = 1)
abstract class HackerNewsRoomDatabase: RoomDatabase(){

    abstract fun hackerNewsDao(): HackerNewsDAO

    companion object{
        @Volatile
        private var INSTANCE: HackerNewsRoomDatabase? = null

        fun getDatabase(context: Context) : HackerNewsRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HackerNewsRoomDatabase::class.java,
                    "hacker_news_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                //Return instance
                instance
            }
        }
    }
}