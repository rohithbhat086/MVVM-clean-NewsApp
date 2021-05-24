package com.rgb.example.newssampleapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.*
import com.rgb.example.newssampleapp.data.model.APIResponse
import com.rgb.example.newssampleapp.data.model.Article
import com.rgb.example.newssampleapp.data.util.Resource
import com.rgb.example.newssampleapp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getTopNewsUseCase: GetTopNewsUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val saveArticleUseCase: SaveArticleUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    private val mTopNews = MutableLiveData<Resource<APIResponse>>()
    val topNews: LiveData<Resource<APIResponse>>
        get() = mTopNews

    fun getTopNews(page: Int) {
        return getNews(page, null)
    }

    fun getSearchedNews(page: Int, query: String?) {
        return getNews(page, query)
    }

    private fun getNews(page: Int, query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            mTopNews.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    if (TextUtils.isEmpty(query)) {
                        mTopNews.postValue(getTopNewsUseCase.execute(page))
                    } else {
                        mTopNews.postValue(getSearchedNewsUseCase.execute(page, query!!))
                    }
                } else {
                    mTopNews.postValue(Resource.Error(message = "Internet not available"))
                }
            } catch (e: Exception) {
                Log.i("MyNews", "EXCEPTION")
                mTopNews.postValue(Resource.Error(message = e.message.toString()))
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            saveArticleUseCase.execute(article)
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteArticleUseCase.execute(article)
        }
    }

    fun getSavedArticles() = liveData {
        getSavedArticlesUseCase.execute().collect {
            emit(it)
        }
    }
}