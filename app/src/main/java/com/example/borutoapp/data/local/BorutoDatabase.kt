package com.example.borutoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.borutoapp.data.local.dao.article.ArticleDao
import com.example.borutoapp.data.local.dao.article.ArticleRemoteKeysDao
import com.example.borutoapp.data.local.dao.hero.HeroDao
import com.example.borutoapp.data.local.dao.hero.HeroRemoteKeysDao
import com.example.borutoapp.domain.model.article.Article
import com.example.borutoapp.domain.model.article.ArticleRemoteKeys
import com.example.borutoapp.domain.model.hero.Hero
import com.example.borutoapp.domain.model.hero.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class, Article::class, ArticleRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class,Converters::class)
abstract class BorutoDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): BorutoDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, BorutoDatabase::class.java)
            } else {
                Room.databaseBuilder(context, BorutoDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
    abstract fun articleDao(): ArticleDao
    abstract fun articleRemoteKeysDao(): ArticleRemoteKeysDao

}