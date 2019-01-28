//package com.andela.d2_news_application.data
//import android.content.ContentProvider
//import android.content.ContentUris
//import android.content.ContentValues
//import android.content.UriMatcher
//import android.database.Cursor
//import android.net.Uri
//import android.util.Log
//import com.andela.d2_news_application.model.ResultsItem
//
//
//
//class DataContentProvider: ContentProvider() {
//
//    companion object {
//        val AUTHORITY = "com.andela.d2_news.application.data.DataContentProvider"
//        private val DATA_TABLE = "articles"
//        val CONTENT_URI : Uri = Uri.parse("content://" + AUTHORITY + "/" +
//                DATA_TABLE)
//    }
//
//    private val ARTICLES = 1
//    private val ARTICLES_ID = 2
//    private val ARTICLES_TABLE = "articles"
//
//    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
//
//    init {
//        uriMatcher.addURI(AUTHORITY, ARTICLES_TABLE, ARTICLES_ID)
//        uriMatcher.addURI(AUTHORITY, ARTICLES_TABLE + "/*", ARTICLES)
//    }
//
//    override fun insert(uri: Uri, values: ContentValues?): Uri? {
//        val uriType = uriMatcher.match(uri)
//        val id: Long
//        when (uriType) {
//            ARTICLES_ID -> {
//                id = LocalDatabase.getInstance(context ?: return null)
//                        .articles().insert(fromContentValues(values!!))
//            }
//            else -> throw IllegalArgumentException("Unknown URI: $uri")
//        }
//        context!!.contentResolver.notifyChange(uri, null)
//        return ContentUris.withAppendedId(uri,  id)
//    }
//
//    override fun query(uri: Uri, p1: Array<String>?, p2: String?,
//                       p3: Array<String>?, p4: String?): Cursor? {
//        val uriType = uriMatcher.match(uri)
//        val context = context ?: return null
//        val cursor: Cursor
//        when(uriType) {
//            ARTICLES -> {
//                val articles = LocalDatabase.getInstance(context).articles()
//                cursor = articles.selectByType(uri.lastPathSegment!!)
//                cursor.setNotificationUri(context.contentResolver, uri)
//                return cursor
//            }
//            else -> throw IllegalArgumentException("Unknown URI: $uri")
//        }
//    }
//
//    override fun onCreate(): Boolean {
//        return true
//    }
//
//    override fun update(uri: Uri, values: ContentValues?, p2: String?, p3: Array<String>?): Int {
//        return 0
//    }
//
//    override fun delete(uri: Uri, p1: String?, p2: Array<String>?): Int {
//        return 0
//    }
//
//    override fun getType(uri: Uri): String? {
//        return null
//    }
//
//    fun fromContentValues(values: ContentValues): ResultsItem{
//        val result = ResultsItem()
//        if (values.containsKey(result.columnId)){
//            result.id = values.getAsLong(result.columnId)
//        }
//        if (values.containsKey("section")) {
//            result.section = values.getAsString("section")
//        }
//        if (values.containsKey("url")){
//            result.url = values.getAsString("url")
//        }
//        if (values.containsKey("createdAt")){
//            result.createdDate = values.getAsString("createdAt")
//        }
//        if (values.containsKey("updatedAt")){
//            result.updatedDate = values.getAsString("updatedAt")
//        }
//        if (values.containsKey("title")) {
//            result.title = values.getAsString("title")
//        }
//        if (values.containsKey("publishedDate")) {
//            result.publishedDate = values.getAsString("publishedDate")
//        }
//        if (values.containsKey("newsType")) {
//            result.newsType = values.getAsString("newsType")
//        }
//        return result
//    }
//
//}