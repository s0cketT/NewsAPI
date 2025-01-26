package com.newsapi.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newsapi.data.database.bookmark.BookmarkDB
import com.newsapi.data.database.bookmark.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "DataBase.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBookmarkDao(database: MainDatabase): BookmarkDao {
        return database.daoBookmark
    }
}

@Database(entities = [BookmarkDB::class], version = 1)
abstract class MainDatabase : RoomDatabase() {
    abstract val daoBookmark: BookmarkDao

}