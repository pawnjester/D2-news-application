package com.andela.d2_news_application.ui.food


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andela.d2_news_application.R
import com.andela.d2_news_application.databinding.FragmentFoodBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_food, container, false
        )
        return binding.root
    }

    companion object {
        fun newInstance() = FoodFragment()
    }


}
