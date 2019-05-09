package com.andela.d2_news_application.data

import com.andela.d2_news_application.data.local.ResultLocalRepositoryImpl
import com.andela.d2_news_application.data.remote.ResultRemoteRespositoryImpl
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
        val localRepo: ResultLocalRepositoryImpl,
        val remoteRepo: ResultRemoteRespositoryImpl) : ResultRepository<ResultsItem,
        FashionResults, FoodResults > {

    override fun getHomeNews(): Observable<List<ResultsItem>> {

        return Observable.concatArrayEager(localRepo.getResultsFromHome(),
                remoteRepo.getHomeFromRemote().materialize()
                        .filter{ !it.isOnError }
                        .dematerialize<List<ResultsItem>>()
                        .doOnNext {
                            localRepo.addResultsToHome(it)
                        })
    }

    override fun getFashionNews(): Observable<List<FashionResults>> {

        return Observable.concatArrayEager(localRepo.getResultsFromFashion(),
                remoteRepo.getFashionFromRemote().materialize()
                        .filter{ !it.isOnError }
                        .dematerialize<List<FashionResults>>()
                        .doOnNext {
                            localRepo.addResultsToFashion(it)
                        })
    }

    override fun getFoodNews(): Observable<List<FoodResults>> {

        return Observable.concatArrayEager(localRepo.getResultsFromFood(),
                remoteRepo.getFoodFromRemote().materialize()
                        .filter{ !it.isOnError }
                        .dematerialize<List<FoodResults>>()
                        .doOnNext {
                            localRepo.addResultsToFood(it)
        })
    }
}