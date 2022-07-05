package com.abdulaziz.nuntium.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdulaziz.nuntium.data.local.dao.NewsDao
import com.abdulaziz.nuntium.data.models.PrimaryNewsUUID
import com.abdulaziz.nuntium.data.models.news_by_category.StringListConverter
import com.abdulaziz.nuntium.data.models.news_by_category.News

@Database(entities = [News::class, PrimaryNewsUUID::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase :RoomDatabase(){

    abstract fun newsDao():NewsDao

}