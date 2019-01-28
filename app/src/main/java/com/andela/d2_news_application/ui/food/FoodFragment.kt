package com.andela.d2_news_application.ui.food


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
import com.andela.d2_news_application.adapter.FoodAdapter
import com.andela.d2_news_application.adapter.HomeAdapter
import com.andela.d2_news_application.databinding.FragmentFoodBinding
import com.andela.d2_news_application.ui.contacts.ContactsFragment
import com.andela.d2_news_application.utils.*
import com.andela.d2_news_application.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_food.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding
    private lateinit var viewModel: SharedViewModel


    private val listAdapter by lazy {
        FoodAdapter({
            goToContactsFragment()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_food, container, false
        )
        with(binding.foodRecycler) {
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
        initViewModel()

        viewModel.foodData.observeForever({

            if (it !== null) {
                listAdapter.updateList(it)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFoodArticles()
        initSwipe()
    }

    companion object {
        fun newInstance() = FoodFragment()
    }

    private fun initViewModel() {
        val factory = InjectorUtils
                .provideSharedViewModelFactory(context!!)
        viewModel = ViewModelProviders
                .of(activity!!, factory).get(SharedViewModel::class.java)
    }

    private fun goToContactsFragment() {
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_container, ContactsFragment.newInstance())
                ?.commit()
    }

    private fun initSwipe() {
        swipeContainerFood.setOnRefreshListener { getFoodArticles() }
    }

    private fun getFoodArticles() {
        food_progress.show()
        viewModel.getFood({
            response, error ->
            viewModel.foodData.value = response
            listAdapter.updateList(response ?: listOf())
            food_progress.dontShow()
            swipeContainerFood.isRefreshing = false

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
