package com.andela.d2_news_application

import android.app.Application
import android.arch.persistence.room.Dao
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.util.Log
import com.andela.d2_news_application.model.ResultsItem
import com.andela.d2_news_application.network.ApiFactory
import com.andela.d2_news_application.utils.CATEGORY
import com.andela.d2_news_application.viewModel.SharedViewModel
import io.reactivex.Observable

//class Repository private constructor(val context: Context) {
//
//    companion object {
//        private var instance: Repository? = null
//
//        fun getInstance(context: Context) =
//                instance ?: synchronized(this) {
//                    instance ?: Repository(context)
//                            .also { instance = it }
//                }
//    }
//
//    val apikey = BuildConfig.API_KEY
//    private val api = ApiFactory(apikey).apiService
//
//    private var contentResolver: ContentResolver? = null
//
//    init {
//        contentResolver = context.contentResolver
//    }
//
//    fun getHomeNews() : Observable<List<ResultsItem>>{
//        return Observable.concatArrayEager(getHomeNewsFromLocal(),
//                getHomeFromRemote().materialize()
//                        .filter{ !it.isOnError }
//                        .dematerialize<List<ResultsItem>>()
//                        .doOnNext {
//                            saveToLocal(CATEGORY.HOME_NEWS, it)
//        })
//    }
//
//    fun getFashionNews(): Observable<List<ResultsItem>> {
//        return Observable.concatArrayEager(getFashionNewsLocal(),
//                getFashionFromRemote().materialize()
//                        .filter{ !it.isOnError }
//                        .dematerialize<List<ResultsItem>>()
//                        .doOnNext {
//                            saveToLocal(CATEGORY.FASHION_NEWS, it)
//                })
//    }
//
//    fun getFoodNews(): Observable<List<ResultsItem>> {
//        return Observable.concatArrayEager(getFoodNewsLocal(),
//                getFoodFromRemote().materialize()
//                        .filter{ !it.isOnError }
//                        .dematerialize<List<ResultsItem>>()
//                        .doOnNext {
//                            saveToLocal(CATEGORY.FOOD_NEWS, it)
//                })
//    }
//
//    private fun saveToLocal(newsType: CATEGORY, resultsItem: List<ResultsItem>){
//
////            resultsItem.map {
////                val contentValues = ContentValues().apply {
////                    put(SharedViewModel.SECTION, it.section)
////                    put(SharedViewModel.TITLE, it.title)
////                    put(SharedViewModel.PUBLISHED_DATE, it.publishedDate)
////                    put(SharedViewModel.URL, it.url)
////                    put(SharedViewModel.CREATED_DATE, it.createdDate)
////                    put(SharedViewModel.UPDATED_DATE, it.updatedDate)
////                    put(SharedViewModel.NEWS_TYPE, newsType.toString())
////                }
////                insertToContentProvider(contentValues, CONTENT_URI)
////                Log.e("tolocal", contentValues.toString())
////            }
//    }
//
//    private fun getHomeNewsFromLocal(): Observable<List<ResultsItem>>{
//        val newsArticles = ArrayList<ResultsItem>()
////        val cursor =
////                contentResolver?.query(Uri.parse("$CONTENT_URI/home_news"),
////                        SharedViewModel.ROWS, null, null, null)
////
////        cursor?.let {
////            while (it.moveToNext()){
////                val articles = ResultsItem().apply {
////                    id = it.getLong(it.getColumnIndex("id"))
////                    section = it.getString(it.getColumnIndex("section"))
////                    url = it.getString(it.getColumnIndex("url"))
////                    createdDate = it.getString(it.getColumnIndex("createdAt"))
////                    publishedDate = it.getString(it.getColumnIndex("publishedDate"))
////                    title = it.getString(it.getColumnIndex("title"))
////                    updatedDate = it.getString(it.getColumnIndex("updatedAt"))
////                    newsType = it.getString(it.getColumnIndex("newsType"))
////                }
////                newsArticles.add(articles)
////            }
////            cursor.close()
////        }
////        Log.e("fromlocal", newsArticles.toString())
//        return Observable.just(newsArticles)
//    }
//
//    private fun getFashionNewsLocal(): Observable<List<ResultsItem>> {
//        val newsArticles = ArrayList<ResultsItem>()
////        val cursor =
////                contentResolver?.query(Uri.parse("$CONTENT_URI/fashion-news"),
////                        SharedViewModel.ROWS, null, null, null)
////
////        cursor?.let {
////            while (it.moveToNext()){
////                val articles = ResultsItem().apply {
////                    id = it.getLong(it.getColumnIndex("id"))
////                    section = it.getString(it.getColumnIndex("section"))
////                    url = it.getString(it.getColumnIndex("url"))
////                    createdDate = it.getString(it.getColumnIndex("createdAt"))
////                    publishedDate = it.getString(it.getColumnIndex("publishedDate"))
////                    title = it.getString(it.getColumnIndex("title"))
////                    updatedDate = it.getString(it.getColumnIndex("updatedAt"))
////                    newsType = it.getString(it.getColumnIndex("newsType"))
////                }
////                newsArticles.add(articles)
////            }
////            cursor.close()
////        }
//        return Observable.just(newsArticles)
//    }
//
//    private fun getFoodNewsLocal(): Observable<List<ResultsItem>> {
//        val newsArticles = ArrayList<ResultsItem>()
////        val cursor =
////                contentResolver?.query(Uri.parse("$CONTENT_URI/food-news"),
////                        SharedViewModel.ROWS, null, null, null)
////
////        cursor?.let {
////            while (it.moveToNext()){
////                val articles = ResultsItem().apply {
////                    id = it.getLong(it.getColumnIndex("id"))
////                    section = it.getString(it.getColumnIndex("section"))
////                    url = it.getString(it.getColumnIndex("url"))
////                    createdDate = it.getString(it.getColumnIndex("createdAt"))
////                    publishedDate = it.getString(it.getColumnIndex("publishedDate"))
////                    title = it.getString(it.getColumnIndex("title"))
////                    updatedDate = it.getString(it.getColumnIndex("updatedAt"))
////                    newsType = it.getString(it.getColumnIndex("newsType"))
////                }
////                newsArticles.add(articles)
////            }
////            cursor.close()
////        }
//        return Observable.just(newsArticles)
//    }
//
//    private fun getHomeFromRemote(): Observable<List<ResultsItem>> {
//        return api.getHomeArticles().flatMap {
//            val items = it.results
//            return@flatMap Observable.fromArray(items)
//        }
//
//    }
//
//    private fun getFashionFromRemote(): Observable<List<ResultsItem>> {
//        return api.getFashion().flatMap {
//            val items = it.results
//            return@flatMap Observable.fromArray(items)
//        }
//    }
//
//
//    private fun getFoodFromRemote(): Observable<List<ResultsItem>> {
//        return api.getFood().flatMap {
//            val items = it.results
//            return@flatMap Observable.fromArray(items)
//        }
//    }
//
//    private fun insertToContentProvider( contentValues: ContentValues, uri: Uri) {
//        contentResolver?.insert(uri, contentValues)
//    }
//
//
//}