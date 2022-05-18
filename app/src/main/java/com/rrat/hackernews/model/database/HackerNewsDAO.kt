package com.rrat.hackernews.model.database

import androidx.room.*
import com.rrat.hackernews.model.data.entities.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface HackerNewsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPost(post: Post)

    @Query("SELECT * FROM POST_TABLE WHERE is_deleted = 0 ORDER BY created_at_i DESC")
    fun getAllPosts(): Flow<List<Post>>

    @Update
    suspend fun updatePost(post: Post)

    @Query("SELECT created_at_i FROM POST_TABLE WHERE created_at_i = :createdAti LIMIT 1")
    fun getItemId(createdAti: Int): List<Int>

    @Transaction
    suspend fun insertOrUpdate(post: Post) {
        val ids = getItemId(post.created_at_i)
        if(ids.isEmpty())
            insertPost(post)
        else
            updatePost(post)
    }
}