package com.newsapi.data.database.bookmark

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmark(bookmark: BookmarkDB)

    @Update
    suspend fun updateBookmark(bookmark: BookmarkDB)

    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkDB)

    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks(): Flow<List<BookmarkDB>>

    @Query("SELECT id FROM bookmark WHERE url = :newUrl LIMIT 1")
    suspend fun getIdByUrl(newUrl: String): Int?


}
