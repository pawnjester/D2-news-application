package com.andela.d2_news_application.viewModel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.andela.d2_news_application.data.ResultRepository
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.data.local.ArticlesDao
import com.andela.d2_news_application.data.local.ResultLocalRepositoryImpl
import com.andela.d2_news_application.data.remote.ResultRemoteRespositoryImpl
import com.andela.d2_news_application.model.FashionResults
import com.andela.d2_news_application.model.FoodResults
import com.andela.d2_news_application.model.ResultsItem
import com.andela.d2_news_application.network.ApiService
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.mockito.Mockito.*
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.*
import java.util.concurrent.Executor

class RxImmediateSchedulerRule : TestRule {
    private val immediate = object : Scheduler() {
        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler { immediate }
                RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
                RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}


class SharedViewModelTest {

//    @Mock
//    lateinit var repository: ResultRepositoryImpl
//
//
//    @Mock
//    lateinit var localRepository: ResultLocalRepositoryImpl
//
//    @Mock
//    lateinit var remoteRepository: ResultRemoteRespositoryImpl
//
    @Mock
    lateinit var viewModel: SharedViewModel
//
//    @Mock
//    lateinit var dao: ArticlesDao
//
//    @Mock
//    lateinit var api: ApiService

    @Mock lateinit var repository: ResultRepository

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
//        localRepository = ResultLocalRepositoryImpl(dao)
//        remoteRepository = ResultRemoteRespositoryImpl(api)
//        repository = ResultRepositoryImpl(localRepository, remoteRepository)
        viewModel = spy(SharedViewModel(repository))
    }

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    fun createResultsStub() = Observable.just(listOf(ResultsItem(), ResultsItem()))

    @Test
    fun getHome() {
        //given
        `when`(repository.getHomeNews()).thenReturn(createResultsStub())
//        val callback : (List<ResultsItem>?, Throwable? ) -> Unit
//        viewModel.getHome{ list, err ->
//            verify(list?.size.e)
//        }

//        verify(lis)
//        val data =
//                listOf(mock(ResultsItem::class.java), mock(ResultsItem::class.java))
//        viewModel.getHome{list, error -> assert(data.isNotEmpty()) }
    }


//    @Test
//    fun getHome2() {
//        mock your extception
//        doReturn(Observable.error(throwa)).`when`(repository.getHomeNews())
//        viewModel.getHome{ list, err ->
//            verify(list?.size.e)
//            verify that the throwable is equal to err
//                    verify that list is null
//        }
//
////        verify(lis)
////        val data =
////                listOf(mock(ResultsItem::class.java), mock(ResultsItem::class.java))
////        viewModel.getHome{list, error -> assert(data.isNotEmpty()) }
//    }

//    @Test
//    fun getFood() {
//        val data =
//                listOf(mock(FoodResults::class.java), mock(FoodResults::class.java))
//        viewModel.getFood{list, error -> assert(data.isNotEmpty()) }
//    }
//
//    @Test
//    fun getFashion() {
//        val data =
//                listOf(mock(FashionResults::class.java), mock(FashionResults::class.java))
//        viewModel.getFashion{list, error -> assert(data.isNotEmpty()) }
//    }

    @Test
    fun clearDisposables() {
        viewModel.clearDisposables()
        verify(viewModel).clearDisposables()
        verify(viewModel, times(1)).clearDisposables()
    }

}