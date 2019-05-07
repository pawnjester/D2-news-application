package com.andela.d2_news_application.di.component

import com.andela.d2_news_application.di.module.AppModule
import com.andela.d2_news_application.di.module.DatabaseModule
import com.andela.d2_news_application.di.module.NetworkModule
import com.andela.d2_news_application.ui.MainActivity
import com.andela.d2_news_application.ui.fashion.FashionFragment
import com.andela.d2_news_application.ui.food.FoodFragment
import com.andela.d2_news_application.ui.home.HomeFragment
import com.andela.d2_news_application.viewModel.SharedViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
AppModule::class, DatabaseModule::class, NetworkModule::class])
interface AppComponent {


    fun inject(viewModel: SharedViewModel)
    fun inject (home: MainActivity)
//    fun inject (fashionFragment: FashionFragment)
//    fun inject(food: FoodFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder

        fun databaseModule(databaseModule: DatabaseModule): Builder

        fun appModule(appModule: AppModule) :Builder
    }
}