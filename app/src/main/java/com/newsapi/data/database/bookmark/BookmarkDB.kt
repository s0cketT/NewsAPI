package com.newsapi.data.database.bookmark

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkDB(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val sourceName: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)
