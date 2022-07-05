package com.abdulaziz.nuntium.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.abdulaziz.nuntium.data.models.news_by_category.CompanyConverter

@Entity
data class PrimaryNewsUUID(
    @PrimaryKey
    val id :Int = 0,
    @TypeConverters(CompanyConverter::class)
    val list: List<String>?=null
)