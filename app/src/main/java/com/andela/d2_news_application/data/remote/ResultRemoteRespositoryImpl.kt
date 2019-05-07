package com.andela.d2_news_application.data.remote

import com.andela.d2_news_application.BuildConfig
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import com.andela.d2_news_application.network.ApiFactory
import com.andela.d2_news_application.network.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class ResultRemoteRespositoryImpl: ResultRemoteRepository {


//    val apikey = BuildConfig.API_KEY
//    private val api = ApiFactory(apikey).apiService

    @Inject
    lateinit var api: ApiService


    override fun getHomeFromRemote(): Observable<List<ResultsItem>> {
        return api.getHomeArticles().flatMap {
            val items = it.results
            return@flatMap Observable.fromArray(items)
        }
    }

    override fun getFoodFromRemote(): Observable<List<FoodResults>> {
        return api.getFood().flatMap {
            val items = it.results
            return@flatMap Observable.fromArray(items)
        }
    }

    override fun getFashionFromRemote(): Observable<List<FashionResults>> {
        return api.getFashion().flatMap {
            val items = it.results
            return@flatMap Observable.fromArray(items)
        }
    }



}