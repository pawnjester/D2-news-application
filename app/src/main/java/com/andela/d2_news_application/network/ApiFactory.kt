package com.andela.d2_news_application.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiFactory(val apiKey: String = "") {

    val apiService: ApiService
        get() {
            val modifiedOkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(apiKeyInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
            val retrofitBuilder = Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .client(modifiedOkHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
            val retrofit = retrofitBuilder.build()
            return retrofit.create(ApiService::class.java)
        }


    private val apiKeyInterceptor: Interceptor
        get() = Interceptor { chain ->
            val original = chain.request()
            val request = original.url()

            val url = request.newBuilder()
                    .addQueryParameter("api-key", apiKey)
                    .build()

            val requestBuilder = original.newBuilder().url(url)
            val requested = requestBuilder.build()

            chain.proceed(requested)

        }

    private val httpLoggingInterceptor: Interceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }
}