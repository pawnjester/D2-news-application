package com.andela.d2_news_application.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andela.d2_news_application.R
import com.andela.d2_news_application.databinding.SingleFoodItemBinding
import com.andela.d2_news_application.databinding.SingleItemBinding
import com.andela.d2_news_application.model.FoodResults

class FoodAdapter (val onClick : (item: FoodResults, view: View) -> Unit): RecyclerView.Adapter<FoodAdapter.HomeViewHolder>() {

    private var resultItems = mutableListOf<FoodResults>()

    fun updateList(update: List<FoodResults>) {
        resultItems.clear()
        resultItems.addAll(update)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleFoodItemBinding>(
                inflater, R.layout.single_food_item, parent, false
        )
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = resultItems.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = resultItems[position]
        holder.bind(createOnClick(item), item)
    }

    inner class HomeViewHolder(val binding: SingleFoodItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: FoodResults) {
            binding.item = item
            binding.clicklistener = listener
            binding.executePendingBindings()
        }
    }

    private fun createOnClick(item: FoodResults): View.OnClickListener {
        return View.OnClickListener {
            onClick(item, it)
        }
    }
}