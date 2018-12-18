package com.andela.d2_news_application.ui.home


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andela.d2_news_application.R
import com.andela.d2_news_application.adapter.CommonAdapter
import com.andela.d2_news_application.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val listAdapter by lazy {
        CommonAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home,
                container, false )

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

    companion object {
        fun newInstance() = HomeFragment()
    }


}
