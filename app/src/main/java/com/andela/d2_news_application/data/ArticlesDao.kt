package com.andela.d2_news_application.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.database.Cursor
import com.andela.d2_news_application.model.ResultsItem

@Dao
interface ArticlesDao {

    @Insert
    fun insert(result: ResultsItem) : Long

    @Update
    fun update(result: ResultsItem): Int

    @Query("select * from articles")
    fun selectAll(): Cursor

    @Query("select * from articles where id=:id")
    fun selectById(id: Long): Cursor

    @Query("delete from articles where id=:id")
    fun deleteById(id: Long): Int
}