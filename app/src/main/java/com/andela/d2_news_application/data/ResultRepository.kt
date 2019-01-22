package com.andela.d2_news_application.data

import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable

interface ResultRepository {

    fun getHomeNews() : Observable<List<ResultsItem>>
    fun getFashionNews() : Observable<List<FashionResults>>
    fun getFoodNews() : Observable<List<FoodResults>>
}