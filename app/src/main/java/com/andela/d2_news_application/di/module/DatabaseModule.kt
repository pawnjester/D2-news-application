package com.andela.d2_news_application.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.andela.d2_news_application.data.local.ArticlesDao
import com.andela.d2_news_application.data.local.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): LocalDatabase = Room
            .databaseBuilder(context.applicationContext,
            LocalDatabase::class.java, "Lms-Database")
            .allowMainThreadQueries()
            .build()

    @Provides
    fun providesArticlesDao(database: LocalDatabase) : ArticlesDao = database.articles()


}