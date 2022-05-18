package com.rrat.hackernews.model.database

import androidx.annotation.WorkerThread
import com.rrat.hackernews.model.data.entities.Post
import kotlinx.coroutines.flow.Flow

class HackerNewsRepository(private val hackerNewsDAO: HackerNewsDAO) {

    @WorkerThread
    suspend fun insertPost(post: Post){
        hackerNewsDAO.insertPost(post)
    }

    val allPost: Flow<List<Post>> = hackerNewsDAO.getAllPosts()


    @WorkerThread
    suspend fun updatePost(post: Post)
    {
        hackerNewsDAO.updatePost(post)
    }

    @WorkerThread
    suspend fun insertOrUpdatePost(post: Post)
    {
        hackerNewsDAO.insertOrUpdate(post)
    }
}