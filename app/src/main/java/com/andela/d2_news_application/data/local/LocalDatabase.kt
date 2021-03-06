package com.andela.d2_news_application.data.local

import android.arch.persistence.room.*
import android.content.Context
import com.andela.d2_news_application.model.Converters
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem

@Database(entities = [ResultsItem::class, FoodResults::class, FashionResults::class], version = 1)
@TypeConverters(Converters::class)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun articles(): ArticlesDao

//    companion object {
//        private var instance: LocalDatabase? = null
//
//        fun getInstance(context: Context): LocalDatabase {
//            if (instance == null) {
//                synchronized(LocalDatabase::class) {
//                    instance = Room.databaseBuilder(context.applicationContext,
//                            LocalDatabase::class.java, "Lms-Database")
//                            .allowMainThreadQueries()
//                            .build()
//                }
//                return instance!!
//            }
//            return instance!!
//        }
//    }
}