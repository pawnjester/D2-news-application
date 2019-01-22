package com.andela.d2_news_application.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.database.Cursor
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem

@Dao
interface ArticlesDao {

    @Insert
    fun insertHomeNews(result: List<ResultsItem>)

    @Insert
    fun insertFashionNews(result: List<FashionResults>)

    @Insert
    fun insertFoodNews(result: List<FoodResults>)

    @Query("select * from articles")
    fun selectAllFromHome(): List<ResultsItem>

    @Query("select * from foodArticles")
    fun selectAllFromFood(): List<FoodResults>

    @Query("select * from fahionArticles")
    fun selectAllFromFashion(): List<FashionResults>

    @Query("select * from articles where newsType=:type")
    fun selectByType(type: String): List<ResultsItem>
}