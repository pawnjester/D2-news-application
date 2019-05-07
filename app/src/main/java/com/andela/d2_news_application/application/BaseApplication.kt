package com.andela.d2_news_application.application

import android.app.Application
import com.andela.d2_news_application.di.component.AppComponent
import com.andela.d2_news_application.di.component.DaggerAppComponent
import com.andela.d2_news_application.di.module.AppModule
import com.andela.d2_news_application.di.module.DatabaseModule
import com.andela.d2_news_application.di.module.NetworkModule

class BaseApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger(this)
    }

    private fun initDagger(app: Application): AppComponent {
        appComponent = DaggerAppComponent.builder()
                .networkModule(NetworkModule)
                .appModule(AppModule(app))
                .databaseModule(DatabaseModule())
                .build()
        return appComponent
    }
}