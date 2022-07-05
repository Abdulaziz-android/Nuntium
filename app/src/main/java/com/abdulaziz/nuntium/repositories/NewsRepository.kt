package com.abdulaziz.nuntium.repositories

import com.abdulaziz.nuntium.data.local.dao.NewsDao
import com.abdulaziz.nuntium.data.models.PrimaryNewsUUID
import com.abdulaziz.nuntium.data.models.news_by_category.News
import com.abdulaziz.nuntium.data.remote.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) {

    suspend fun getNewsByCategory(category: String, language: String) =
        flow {
            emit(apiService.getNews(category = category, language = language))
        }
    fun insertPrimaryUUID(primaryNewsUUID: PrimaryNewsUUID) = newsDao.insert(primaryNewsUUID)
    fun getPrimaryUUID() = newsDao.getPrimaryUUID()


    fun insertNews(news: News) = newsDao.insert(news)
    fun getNewsByUUID(uuid: String) = newsDao.getNewsByUUID(uuid)

    suspend fun getSearchedNews(string: String, language: String) = flow {
        emit(apiService.getSearchedNews(search = string, language = language))
    }


}