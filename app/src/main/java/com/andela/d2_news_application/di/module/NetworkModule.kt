package com.andela.d2_news_application.di.module

import com.andela.d2_news_application.network.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.url()

            val url = request.newBuilder()
                    .addQueryParameter("api-key", "5e8efaecfe81493b8ef3fb9aa43eeb02")
                    .build()

            val requestBuilder = original.newBuilder().url(url)
            val requested = requestBuilder.build()

            chain.proceed(requested)
        }
    }

    @Provides
    fun providesHttpInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun provideOkHttp(apiKeyInterceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun provideRetrofit(modifiedOkHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .client(modifiedOkHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesApiService(retrofit: Retrofit) : ApiService =
            retrofit.create(ApiService::class.java)

}