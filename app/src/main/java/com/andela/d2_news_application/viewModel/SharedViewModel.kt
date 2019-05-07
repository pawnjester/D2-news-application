package com.andela.d2_news_application.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.andela.d2_news_application.data.ResultRepository
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.model.ContactsModel
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SharedViewModel(val repository: ResultRepository): ViewModel() {


    var disposable: Disposable? = null

    val homeData by lazy {
        MutableLiveData<List<ResultsItem>>()
    }

//    val viewCommands =

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

    companion object {
        val SECTION =  "section"
        val TITLE = "title"
        val PUBLISHED_DATE = "publishedDate"
        val URL = "url"
        val CREATED_DATE = "createdAt"
        val UPDATED_DATE = "updatedAt"
        val ID = "id"
        val NEWS_TYPE = "newsType"

        val ROWS = arrayOf(SECTION, TITLE, PUBLISHED_DATE,
                URL, CREATED_DATE, UPDATED_DATE)
    }



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


    fun clearDisposables() = disposable?.dispose()

}
