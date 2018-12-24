package com.andela.d2_news_application.ui.food


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andela.d2_news_application.R
import com.andela.d2_news_application.adapter.CommonAdapter
import com.andela.d2_news_application.databinding.FragmentFoodBinding
import com.andela.d2_news_application.utils.dontShow
import com.andela.d2_news_application.utils.show
import com.andela.d2_news_application.utils.showToast
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
        CommonAdapter()
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFoodArticles()
    }

    companion object {
        fun newInstance() = FoodFragment()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders
                .of(activity!!).get(SharedViewModel::class.java)
    }

    private fun getFoodArticles() {
        food_progress.show()
        viewModel.getFoodData({
            response, error ->
            listAdapter.updateList(response?.results!!)
            food_progress.dontShow()

            if (error != null) context?.showToast("Error retrieving articles")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposables()
    }
}
