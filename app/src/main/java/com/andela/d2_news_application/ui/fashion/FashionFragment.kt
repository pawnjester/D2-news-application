package com.andela.d2_news_application.ui.fashion


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
import com.andela.d2_news_application.databinding.FragmentFashionBinding
import com.andela.d2_news_application.utils.*
import com.andela.d2_news_application.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_fashion.*


/**
 * A simple [Fragment] subclass.
 *
 */
class FashionFragment : Fragment() {

    private lateinit var binding: FragmentFashionBinding

    private lateinit var viewModel: SharedViewModel

    private val listAdapter by lazy {
        CommonAdapter()
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
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFahionArticles()
    }


    companion object {
        fun newInstance() = FashionFragment()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders
                .of(activity!!).get(SharedViewModel::class.java)
    }

    private fun getFahionArticles() {
        fashion_progress.show()
        val isConnected = CheckConnection(activity!!).isConnected()
        if (isConnected) {
            viewModel.getFashion({
                response, error ->
                listAdapter.updateList(response?.results!!)
                fashion_progress.dontShow()

                if (error != null) context?.showToast("Error retrieving articles")
            })
        } else {
            fashion_container.showSnackbar("No internet Connection")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposables()
    }
}
