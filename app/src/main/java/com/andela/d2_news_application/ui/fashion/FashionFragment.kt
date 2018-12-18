package com.andela.d2_news_application.ui.fashion


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andela.d2_news_application.R
import com.andela.d2_news_application.databinding.FragmentFashionBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class FashionFragment : Fragment() {

    private lateinit var binding: FragmentFashionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_fashion, container, false)
        return binding.root
    }


    companion object {
        fun newInstance() = FashionFragment()
    }
}
