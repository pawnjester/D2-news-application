package com.andela.d2_news_application.network

import com.andela.d2_news_application.model.ApiResponse
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    companion object {
        val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
    }

    @GET("food.json")
    fun getFood(): Observable<ApiResponse<List<FoodResults>>>

    @GET("home.json")
    fun getHomeArticles(): Observable<ApiResponse<List<ResultsItem>>>

    @GET("fashion.json")
    fun getFashion(): Observable<ApiResponse<List<FashionResults>>>
}