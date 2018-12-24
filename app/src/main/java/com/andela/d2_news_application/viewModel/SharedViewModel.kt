package com.andela.d2_news_application.viewModel

import android.arch.lifecycle.ViewModel
import com.andela.d2_news_application.BuildConfig
import com.andela.d2_news_application.model.ApiResponse
import com.andela.d2_news_application.model.ResultsItem
import com.andela.d2_news_application.network.ApiFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SharedViewModel: ViewModel() {

    val apikey = BuildConfig.API_KEY
    private val api = ApiFactory(apikey).apiService

    var disposable: Disposable? = null


    fun getHomeData(onDataObtained: (ApiResponse<List<ResultsItem>>?, Throwable?) -> Unit) {
       disposable = api.getHomeArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
               .subscribe({
                   home ->
                   onDataObtained(home, null)
               }, { error ->
                   onDataObtained(null, error)
               })

    }

    fun getFoodData(onDataObtained: (ApiResponse<List<ResultsItem>>?, Throwable?) -> Unit) {
        disposable = api.getFood()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    home ->
                    onDataObtained(home, null)
                }, { error ->
                    onDataObtained(null, error)
                })
    }

    fun getFashionData(onDataObtained: (ApiResponse<List<ResultsItem>>?, Throwable?) -> Unit) {
        disposable = api.getFashion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    home ->
                    onDataObtained(home, null)
                }, { error ->
                    onDataObtained(null, error)
                })
    }

    fun clearDisposables() = disposable?.dispose()


}