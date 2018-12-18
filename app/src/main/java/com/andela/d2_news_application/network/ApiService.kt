package com.andela.d2_news_application.network

import com.andela.d2_news_application.model.ApiResponse
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    companion object {
        val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
    }

    @GET("food.json")
    fun getFood(): Single<ApiResponse<List<ResultsItem>>>

    @GET("home.json")
    fun getHomeArticles(): Single<ApiResponse<List<ResultsItem>>>

    @GET("fashion.json")
    fun getFashion(): Single<ApiResponse<List<ResultsItem>>>
}