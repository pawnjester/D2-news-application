package com.andela.d2_news_application.data.local

import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable

interface ResultLocalRepository {

    fun getAllResults(newsType: String) : Observable<List<ResultsItem>>

    fun getResultsFromHome() : Observable<List<ResultsItem>>
    fun getResultsFromFood() : Observable<List<FoodResults>>
    fun getResultsFromFashion() : Observable<List<FashionResults>>

    fun addResultsToHome(users: List<ResultsItem>)
    fun addResultsToFashion(users: List<FashionResults>)
    fun addResultsToFood(users: List<FoodResults>)
}