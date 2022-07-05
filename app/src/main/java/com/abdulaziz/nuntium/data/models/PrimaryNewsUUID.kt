package com.abdulaziz.nuntium.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abdulaziz.nuntium.data.models.news_by_category.StringListConverter

@Entity
data class PrimaryNewsUUID(
    @PrimaryKey
    val id :Int = 0,
    @TypeConverters(StringListConverter::class)
    val list: List<String>?=null
)