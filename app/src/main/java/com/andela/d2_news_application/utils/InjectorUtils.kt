package com.andela.d2_news_application.utils

import android.content.Context
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.data.local.LocalDatabase
import com.andela.d2_news_application.data.local.ResultLocalRepositoryImpl
import com.andela.d2_news_application.data.remote.ResultRemoteRespositoryImpl
import com.andela.d2_news_application.ui.ViewModelFactory
import javax.inject.Inject

class InjectorUtils {

    @Inject
    lateinit var repoLocal : ResultLocalRepositoryImpl

    private fun getLocalRepository(app: Context): ResultLocalRepositoryImpl {
        return repoLocal
    }

    private fun getRemoteRepository() : ResultRemoteRespositoryImpl {
        return ResultRemoteRespositoryImpl()
    }

    private fun getResultRepository(app: Context) : ResultRepositoryImpl {
        return ResultRepositoryImpl(getLocalRepository(app), getRemoteRepository())
    }

    fun provideSharedViewModelFactory(app: Context): ViewModelFactory {
        return ViewModelFactory(getResultRepository(app))
    }
}