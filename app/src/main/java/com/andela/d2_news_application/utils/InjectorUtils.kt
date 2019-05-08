package com.andela.d2_news_application.utils

import android.content.Context
import com.andela.d2_news_application.data.ResultRepository
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.data.local.ArticlesDao
import com.andela.d2_news_application.data.local.LocalDatabase
import com.andela.d2_news_application.data.local.ResultLocalRepositoryImpl
import com.andela.d2_news_application.data.remote.ResultRemoteRespositoryImpl
import com.andela.d2_news_application.ui.ViewModelFactory
import javax.inject.Inject

class InjectorUtils @Inject constructor(val dao: ArticlesDao) {



//    private fun getLocalRepository(): ResultLocalRepositoryImpl {
//        return ResultLocalRepositoryImpl(dao)
//    }
//
//    private fun getRemoteRepository() : ResultRemoteRespositoryImpl {
//        return ResultRemoteRespositoryImpl()
//    }
//
//    private fun getResultRepository() : ResultRepositoryImpl {
//        return ResultRepositoryImpl(getLocalRepository(), getRemoteRepository())
//    }

    fun provideSharedViewModelFactory(result: ResultRepositoryImpl): ViewModelFactory {
        return ViewModelFactory(result)
    }
}