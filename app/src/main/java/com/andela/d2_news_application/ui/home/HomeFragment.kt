package com.andela.d2_news_application.ui.home


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andela.d2_news_application.R
import com.andela.d2_news_application.adapter.HomeAdapter
import com.andela.d2_news_application.application.BaseApplication
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.databinding.FragmentHomeBinding
import com.andela.d2_news_application.model.ResultsItem
import com.andela.d2_news_application.ui.contacts.ContactsFragment
import com.andela.d2_news_application.utils.*
import com.andela.d2_news_application.viewModel.SharedViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: SharedViewModel
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var injectorUtils: InjectorUtils

    @Inject
    lateinit var result: ResultRepositoryImpl

    private val listAdapter by lazy {
        HomeAdapter({
            viewModel.homeItem = it
            goToContactsFragment()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home,
                container, false )
        binding.vm = viewModel
        viewModel.viewCommmands.subscribe {
            when(it) {
                is SharedViewModel.ViewActions.Loading -> home_progress.show()
                is SharedViewModel.ViewActions.notLoading -> home_progress.dontShow()
                is SharedViewModel.ViewActions.displayArticles -> listAdapter.updateList(it.items)
            }
        }.addToCompositeDisposable(compositeDisposable)

        with(binding.homeRecycler) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false)
            adapter = listAdapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApplication.appComponent.inject(this)
        initViewModel()

        viewModel.homeData.observeForever {
            if (it !== null) {
                listAdapter.updateList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getHomeArticles()
        initSwipe()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    private fun initSwipe() {
//        swipeContainerHome.setOnRefreshListener { getHomeArticles() }
    }

    private fun initViewModel() {
        val factory = injectorUtils
                .provideSharedViewModelFactory(result)
        viewModel = ViewModelProviders
                .of(activity!!, factory).get(SharedViewModel::class.java)
    }

    private fun goToContactsFragment() {
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_container, ContactsFragment.newInstance())
                ?.commit()
    }

    private fun getHomeArticles() {
//        home_progress.show()
        viewModel.getHome()
//        viewModel.getHome({
//            response, error ->
//            if (response!!.isNotEmpty()) {
//                viewModel.homeData.value = response
//                listAdapter.updateList(response)
//                home_progress.dontShow()
//                swipeContainerHome.isRefreshing = false
//                binding.noArticles.dontShow()
//            }else {
//                binding.noArticles.show()
//                home_progress.dontShow()
//            }
//
//            if (error != null) {
//                context?.showToast("Error retrieving articles")
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposables()
    }

}
