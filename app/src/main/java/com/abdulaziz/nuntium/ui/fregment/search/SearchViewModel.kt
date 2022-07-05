package com.abdulaziz.nuntium.ui.fregment.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulaziz.nuntium.repositories.NewsRepository
import com.abdulaziz.nuntium.utils.NetworkHelper
import com.abdulaziz.nuntium.utils.NewsResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private var _stateFlow = MutableStateFlow<NewsResource>(NewsResource.Idle)
    val stateFlow: StateFlow<NewsResource> get() = _stateFlow

    fun getSearchedNews(string:String, language:String){
        if (networkHelper.isNetworkConnected()){
            _stateFlow.value = NewsResource.Loading
            viewModelScope.launch {
                repository.getSearchedNews(string, language).catch {
                    _stateFlow.value = NewsResource.Error(it.message ?: "")
                }.collect {
                    if (it.isSuccessful){
                        _stateFlow.value = NewsResource.Success(it.body()?.data!!)
                    }else{
                        _stateFlow.value = NewsResource.Error(it.message() ?: "")
                    }
                }
            }
        }else {
            _stateFlow.value = NewsResource.Error("Internet not connected!")
        }
    }
}