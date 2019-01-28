package com.andela.d2_news_application.data.remote

import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable

interface ResultRemoteRepository {

    fun getHomeFromRemote() : Observable<List<ResultsItem>>

    fun getFoodFromRemote(): Observable<List<FoodResults>>

    fun getFashionFromRemote(): Observable<List<FashionResults>>
}