package com.andela.d2_news_application.utils

import android.content.Context
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.data.local.LocalDatabase
import com.andela.d2_news_application.data.local.ResultLocalRepositoryImpl
import com.andela.d2_news_application.data.remote.ResultRemoteRespositoryImpl
import com.andela.d2_news_application.ui.ViewModelFactory

object InjectorUtils {

    private fun getLocalRepository(app: Context): ResultLocalRepositoryImpl {
        return ResultLocalRepositoryImpl.getInstance(LocalDatabase.getInstance(app).articles())
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