package com.andela.d2_news_application.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.andela.d2_news_application.model.ResultsItem

@Database(entities = arrayOf(ResultsItem::class), version = 1)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun articles(): ArticlesDao

    companion object {
        private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            if (instance == null) {
                synchronized(LocalDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            LocalDatabase::class.java, "LMSDatabase")
                            .build()
                }
                return instance!!
            }
            return instance!!
        }
    }
}