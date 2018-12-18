package com.andela.d2_news_application.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.andela.d2_news_application.R
import com.andela.d2_news_application.databinding.SingleItemBinding
import com.andela.d2_news_application.model.ResultsItem

class CommonAdapter: RecyclerView.Adapter<CommonAdapter.HomeViewHolder>() {

    private var resultItems = mutableListOf<ResultsItem>()

    fun updateList(update: List<ResultsItem>) {
        resultItems.clear()
        resultItems.addAll(update)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleItemBinding>(
                inflater, R.layout.single_item, parent, false
        )
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = resultItems.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = resultItems[position]
        holder.bind(item)
    }

    inner class HomeViewHolder(val binding: SingleItemBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResultsItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}