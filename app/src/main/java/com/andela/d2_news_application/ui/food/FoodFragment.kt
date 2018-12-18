package com.andela.d2_news_application.ui.food


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

/**
 * A simple [Fragment] subclass.
 *
 */
class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding

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

    companion object {
        fun newInstance() = FoodFragment()
    }


}
