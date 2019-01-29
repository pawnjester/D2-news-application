package com.andela.d2_news_application.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class Converters {

    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<MultimediaItem> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<MultimediaItem>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<MultimediaItem>): String {
        return gson.toJson(someObjects)
    }

}