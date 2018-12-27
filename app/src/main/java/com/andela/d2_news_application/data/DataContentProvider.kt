package com.andela.d2_news_application.data
import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import com.andela.d2_news_application.model.ResultsItem



class DataContentProvider: ContentProvider() {

    companion object {
        val AUTHORITY = "com.andela.d2_news.application.data.DataContentProvider"
        private val DATA_TABLE = "articles"
        val CONTENT_URI : Uri = Uri.parse("content://" + AUTHORITY + "/" +
                DATA_TABLE)
    }

    private val ARTICLES = 1
    private val GET_ARTICLES_ID = 2

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, "articles", ARTICLES)
        uriMatcher.addURI(AUTHORITY, "articles", GET_ARTICLES_ID)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val uriType = uriMatcher.match(uri)
        val id: Long
        when (uriType) {
            ARTICLES -> id = LocalDatabase.getInstance(context)
                        .articles().insert(fromContentValues(values!!))
            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }
        context!!.contentResolver.notifyChange(uri, null)
        return ContentUris.withAppendedId(uri, id)
    }

    override fun query(uri: Uri, p1: Array<String>?, p2: String?,
                       p3: Array<String>?, p4: String?): Cursor? {
        val uriType = uriMatcher.match(uri)
        if (uriType == GET_ARTICLES_ID || uriType == ARTICLES) {
            val context = context ?: return null
            val articles = LocalDatabase.getInstance(context).articles()
            val cursor: Cursor
            if (uriType == ARTICLES) {
                cursor = articles.selectAll()
            } else {
                cursor = articles.selectById(ContentUris.parseId(uri))
            }
            cursor.setNotificationUri(context.contentResolver, uri)
            return cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, p2: String?, p3: Array<String>?): Int {
        when (uriMatcher.match(uri)) {
            ARTICLES -> throw IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            GET_ARTICLES_ID -> {
                val context = context ?: return 0
                val articles = fromContentValues(values!!)
                articles.id = ContentUris.parseId(uri)
                val count = LocalDatabase.getInstance(context).articles()
                        .update(articles)
                context.contentResolver.notifyChange(uri, null)
                return count
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(uri: Uri, p1: String?, p2: Array<String>?): Int {
        val uriType = uriMatcher.match(uri)
        when (uriType) {
            ARTICLES -> throw IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            GET_ARTICLES_ID -> {
                val context = context ?: return 0
                val count = LocalDatabase.getInstance(context)
                        .articles().deleteById(ContentUris.parseId(uri))
                context.contentResolver.notifyChange(uri, null)
                return count
            }
            else -> throw IllegalArgumentException("Unknown URI: " + uri)
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    fun fromContentValues(values: ContentValues): ResultsItem{
        val result = ResultsItem()
        if (values.containsKey(BaseColumns._ID)){
        result.id = values.getAsLong(BaseColumns._ID)
        }
        return result
    }

}