package com.andela.d2_news_application.di.module

import android.app.Application
import android.content.Context
import com.andela.d2_news_application.data.ResultRepository
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.data.local.ArticlesDao
import com.andela.d2_news_application.data.local.ResultLocalRepositoryImpl
import com.andela.d2_news_application.data.remote.ResultRemoteRespositoryImpl
import com.andela.d2_news_application.utils.InjectorUtils
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    fun providesApplication() = app

    @Provides @Singleton fun providesLocalRespository(dao: ArticlesDao):
            ResultLocalRepositoryImpl =  ResultLocalRepositoryImpl(dao)

    @Provides fun providesRemoteRepository():
            ResultRemoteRespositoryImpl = ResultRemoteRespositoryImpl()

    @Provides fun provideRepository(localRepositoryImpl: ResultLocalRepositoryImpl,
                                    remoteRespositoryImpl: ResultRemoteRespositoryImpl) :
            ResultRepositoryImpl = ResultRepositoryImpl(localRepositoryImpl, remoteRespositoryImpl)

    @Provides @Singleton fun provideInjectorUtils(): InjectorUtils = InjectorUtils()

}