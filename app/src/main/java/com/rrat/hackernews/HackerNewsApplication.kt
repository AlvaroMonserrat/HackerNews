package com.rrat.hackernews

import android.app.Application
import com.rrat.hackernews.model.database.HackerNewsRepository
import com.rrat.hackernews.model.database.HackerNewsRoomDatabase

class HackerNewsApplication: Application() {
    private val database by lazy { HackerNewsRoomDatabase.getDatabase(this@HackerNewsApplication) }
    val repository by lazy { HackerNewsRepository(database.hackerNewsDao()) }
}