package com.andela.d2_news_application

import android.arch.persistence.room.Dao
import com.andela.d2_news_application.data.ArticlesDao

class Repository private constructor(dao: ArticlesDao) {

    companion object {
        private var instance: Repository? = null

        fun getInstance(dao: ArticlesDao) =
                instance ?: synchronized(this) {
                    instance ?: Repository(dao)
                            .also { instance = it }
                }
    }

    fun saveArticles() {

    }

    fun getArticles() {

    }
}