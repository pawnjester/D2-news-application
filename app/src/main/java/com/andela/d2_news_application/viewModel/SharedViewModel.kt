package com.andela.d2_news_application.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import com.andela.d2_news_application.BuildConfig
import com.andela.d2_news_application.data.DataContentProvider
import com.andela.d2_news_application.data.DataContentProvider.Companion.CONTENT_URI
import com.andela.d2_news_application.model.ApiResponse
import com.andela.d2_news_application.model.ResultsItem
import com.andela.d2_news_application.network.ApiFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SharedViewModel(app: Application): AndroidViewModel(app) {

    val apikey = BuildConfig.API_KEY
    private val api = ApiFactory(apikey).apiService

    private val contentResolver: ContentResolver? = null

    var disposable: Disposable? = null

    companion object {
        val SECTION =  "section"
        val TITLE = "title"
        val PUBLISHED_DATE = "publishedDate"
        val URL = "url"
        val CREATED_DATE = "createdAt"
        val UPDATED_DATE = "updatedAt"
    }


    fun getHomeData(onDataObtained: (ApiResponse<List<ResultsItem>>?, Throwable?) -> Unit) {
       disposable = api.getHomeArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
               .subscribe({
                   home ->
                   onDataObtained(home, null)
               }, { error ->
                   onDataObtained(null, error)
               })

    }

    fun getFoodData(onDataObtained: (ApiResponse<List<ResultsItem>>?, Throwable?) -> Unit) {
        disposable = api.getFood()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    home ->
                    onDataObtained(home, null)
                }, { error ->
                    onDataObtained(null, error)
                })
    }

    fun getFashionData(onDataObtained: (ApiResponse<List<ResultsItem>>?, Throwable?) -> Unit) {
        disposable = api.getFashion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    home ->
                    onDataObtained(home, null)
                }, { error ->
                    onDataObtained(null, error)
                })
    }

    private fun saveToDatabase(resultsItem: List<ResultsItem>) {
        resultsItem.map {
            val contentValues = ContentValues().apply {
                put(SECTION, it.section)
                put(TITLE, it.title)
                put(PUBLISHED_DATE, it.publishedDate)
                put(URL, it.url)
                put(CREATED_DATE, it.createdDate)
                put(UPDATED_DATE, it.updatedDate)
            }
            insertToContentProvider(contentValues, CONTENT_URI)
        }
    }

    private fun insertToContentProvider( contentValues: ContentValues, uri: Uri) {
        contentResolver?.insert(uri, contentValues)
    }



    fun getFromDatabase() {

    }

    fun clearDisposables() = disposable?.dispose()


}