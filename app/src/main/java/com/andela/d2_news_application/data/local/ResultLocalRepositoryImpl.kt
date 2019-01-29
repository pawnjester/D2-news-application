package com.andela.d2_news_application.data.local

import android.util.Log
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable

class ResultLocalRepositoryImpl private constructor(val dao: ArticlesDao): ResultLocalRepository {


    override fun getResultsFromHome(): Observable<List<ResultsItem>> {
        return Observable.fromCallable<List<ResultsItem>>({
            dao.selectAllFromHome()
        })
    }

    override fun getResultsFromFood(): Observable<List<FoodResults>> {
        Log.e(">>>", ">>>")
        return Observable.fromCallable<List<FoodResults>>({
            dao.selectAllFromFood()
        })
    }

    override fun getResultsFromFashion(): Observable<List<FashionResults>> {
        return Observable.fromCallable<List<FashionResults>>({
            dao.selectAllFromFashion()
        })
    }

    override fun addResultsToHome(users: List<ResultsItem>) {
        dao.insertHomeNews(users)
    }

    override fun addResultsToFashion(users: List<FashionResults>) {
        dao.insertFashionNews(users)
    }

    override fun addResultsToFood(users: List<FoodResults>) {
        dao.insertFoodNews(users)
    }

    companion object {
        private var instance: ResultLocalRepositoryImpl? = null

        fun getInstance(dao: ArticlesDao) =
                instance ?: synchronized(this) {
                    instance ?: ResultLocalRepositoryImpl(dao)
                            .also { instance = it }
                }
    }

    override fun getAllResults(newsType: String): Observable<List<ResultsItem>> {
        return Observable.fromCallable<List<ResultsItem>>({
            dao.selectByType(newsType)
        })
    }

}