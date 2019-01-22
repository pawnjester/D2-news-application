package com.andela.d2_news_application.data

import android.util.Log
import com.andela.d2_news_application.data.local.ResultLocalRepositoryImpl
import com.andela.d2_news_application.data.remote.ResultRemoteRespositoryImpl
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ResultRepositoryImpl(
        val localRepo: ResultLocalRepositoryImpl,
        val remoteRepo: ResultRemoteRespositoryImpl) : ResultRepository {

    override fun getHomeNews(): Observable<List<ResultsItem>> {
//        return Observable.mergeDelayError(remoteRepo.getHomeFromRemote()
//                .doOnNext {
//            localRepo.addResultsToHome(it)
//        }.subscribeOn(Schedulers.io()),
//                localRepo.getResultsFromHome()
//                        .subscribeOn(Schedulers.io())
//        )
        return localRepo.getResultsFromHome().flatMap {
            return@flatMap if (it.isNotEmpty()) {
                Log.e("local", "repo")
                Observable.fromArray(it)
            } else {
                Log.e("remote", "repo")

                remoteRepo.getHomeFromRemote().flatMap {
                    localRepo.addResultsToHome(it)
                    Observable.just(it)
                }
            }
        }
    }

    override fun getFashionNews(): Observable<List<FashionResults>> {
//        return Observable.mergeDelayError(remoteRepo.getFashionFromRemote()
//                .doOnNext {
//                    localRepo.addResultsToFashion(it)
//                }.subscribeOn(Schedulers.io()),
//                localRepo.getResultsFromFashion().subscribeOn(Schedulers.io()))
        return localRepo.getResultsFromFashion().flatMap {

            if (it.isNotEmpty()) {
                Log.e("local", "repo")

                Observable.fromArray(it)
            } else {
                Log.e("remote", "repo")

                remoteRepo.getFashionFromRemote().flatMap {
                    localRepo.addResultsToFashion(it)
                    Observable.just(it)
                }
            }
        }
    }

    override fun getFoodNews(): Observable<List<FoodResults>> {
//        return Observable.mergeDelayError(remoteRepo.getFoodFromRemote()
//                .doOnNext {
//                    localRepo.addResultsToFood(it)
//                }.subscribeOn(Schedulers.io()),
//                localRepo.getResultsFromFood().subscribeOn(Schedulers.io()))

        return localRepo.getResultsFromFood().flatMap {
            if (it.isNotEmpty()) {
                Log.e("local", "repo")

                Observable.fromArray(it)
            } else {
                Log.e("remote", "repo")

                remoteRepo.getFoodFromRemote().flatMap {
                    localRepo.addResultsToFood(it)
                    Observable.just(it)
                }
            }
        }
    }
}