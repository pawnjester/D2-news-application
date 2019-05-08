package com.andela.d2_news_application.ui.fashion


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
import com.andela.d2_news_application.adapter.FashionAdapter
import com.andela.d2_news_application.adapter.HomeAdapter
import com.andela.d2_news_application.application.BaseApplication
import com.andela.d2_news_application.data.ResultRepositoryImpl
import com.andela.d2_news_application.databinding.FragmentFashionBinding
import com.andela.d2_news_application.ui.contacts.ContactsFragment
import com.andela.d2_news_application.utils.*
import com.andela.d2_news_application.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_fashion.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class FashionFragment : Fragment() {

    private lateinit var binding: FragmentFashionBinding

    private lateinit var viewModel: SharedViewModel

    @Inject
    lateinit var injector: InjectorUtils

    @Inject
    lateinit var result: ResultRepositoryImpl

    private val listAdapter by lazy {
        FashionAdapter({
            goToContactsFragment()
            viewModel.fashionItem = it
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_fashion, container, false)
        with(binding.fashionRecycler) {
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
        viewModel.fashionData.observeForever({
            if (it !== null) {
                listAdapter.updateList(it)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFahionArticles()
        initSwipe()
    }


    companion object {
        fun newInstance() = FashionFragment()
    }

    private fun initSwipe() {
        swipeContainerFashion.setOnRefreshListener { getFahionArticles() }
    }

    private fun initViewModel() {
        val factory = injector
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

    private fun getFahionArticles() {
        fashion_progress.show()
            viewModel.getFashion({
                response, error ->
                response?.let {
                    if (response.isNotEmpty()){
                        viewModel.fashionData.value = response
                        listAdapter.updateList(response)
                        fashion_progress.dontShow()
                        swipeContainerFashion.isRefreshing = false
                        binding.noArticles.dontShow()
                    }else{
                        binding.noArticles.show()
                        fashion_progress.dontShow()
                    }
                }

                if (error != null) {
                    context?.showToast("Error retrieving articles")
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposables()
    }
}
