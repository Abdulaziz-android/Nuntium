package com.abdulaziz.nuntium.data.local.dao

import androidx.room.*
import com.abdulaziz.nuntium.data.models.PrimaryNewsUUID
import com.abdulaziz.nuntium.data.models.news_by_category.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)

    @Query("select * from news")
    fun getAllNews(): List<News>

    @Query("select * from news where uuid = :uuid")
    fun getNewsByUUID(uuid: String): News?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(primaryNewsUUID: PrimaryNewsUUID)

    @Query("select * from primarynewsuuid where id = 0")
    fun getPrimaryUUID():PrimaryNewsUUID?
}