package com.andela.d2_news_application.utils

import android.content.Context
import android.net.ConnectivityManager

class CheckConnection(val context: Context) {

    fun isConnected(): Boolean {

        val connectionManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        return connectionManager?.activeNetworkInfo != null
                && connectionManager.activeNetworkInfo?.isConnected!!
    }
}