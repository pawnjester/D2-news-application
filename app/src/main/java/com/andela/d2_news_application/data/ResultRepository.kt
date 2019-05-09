package com.andela.d2_news_application.data

import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable

interface ResultRepository<T, R, F> {

    fun getHomeNews() : Observable<List<T>>
    fun getFashionNews() : Observable<List<R>>
    fun getFoodNews() : Observable<List<F>>
}