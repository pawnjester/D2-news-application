package com.andela.d2_news_application.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.andela.d2_news_application.data.ResultRepository
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.di.component.AppComponent
import com.andela.d2_news_application.di.component.DaggerAppComponent
import com.andela.d2_news_application.di.module.AppModule
import com.andela.d2_news_application.di.module.DatabaseModule
import com.andela.d2_news_application.di.module.NetworkModule
import com.andela.d2_news_application.model.ContactsModel
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SharedViewModel @Inject constructor(val repository: ResultRepository<ResultsItem,
        FashionResults, FoodResults>): ViewModel() {


    var disposable: Disposable? = null


    val homeData by lazy {
        MutableLiveData<List<ResultsItem>>()
    }

    val fashionData by lazy {
        MutableLiveData<List<FashionResults>>()
    }

    val foodData by lazy {
        MutableLiveData<List<FoodResults>>()
    }

    var foodItem: FoodResults ? = null
    var homeItem: ResultsItem ? = null
    var fashionItem: FashionResults ? = null
    var contactItem: ContactsModel ? = null


    fun getHome(onDataObtained: (List<ResultsItem>?, Throwable?) -> Unit)  {

        disposable = repository.getHomeNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    onDataObtained(it, null)
                },  {
                    onDataObtained(null, it)
                })
    }

    fun getFood(onDataObtained: (List<FoodResults>?, Throwable?) -> Unit) {

        disposable = repository.getFoodNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                    .subscribe({
                    onDataObtained(it, null)
                },  {
                    onDataObtained(null, it)
                })
    }

    fun getFashion(onDataObtained: (List<FashionResults>?, Throwable?) -> Unit) {

        disposable = repository.getFashionNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    onDataObtained(it, null)
                },  {
                    onDataObtained(null, it)
                })

    }

//    sealed class ViewActions {
//
//    }


    fun clearDisposables() = disposable?.dispose()

}
