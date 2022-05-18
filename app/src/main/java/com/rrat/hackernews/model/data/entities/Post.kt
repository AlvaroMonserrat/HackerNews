package com.rrat.hackernews.model.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey val created_at_i: Int,
    @ColumnInfo val story_title: String?,
    @ColumnInfo val author: String?,
    @ColumnInfo val created_at: String?,
    @ColumnInfo val story_url: String?,
    @ColumnInfo val is_deleted: Boolean = false
)
