package com.andela.d2_news_application.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andela.d2_news_application.R
import com.andela.d2_news_application.databinding.SingleContactLayoutBinding
import com.andela.d2_news_application.model.ContactsModel

class ContactsAdapter(val onClick: (item: ContactsModel) -> Unit)
    : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    private var contacts = mutableListOf<ContactsModel>()

    fun updateList(update: List<ContactsModel>) {
        contacts.clear()
        contacts.addAll(update)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleContactLayoutBinding>(
                inflater, R.layout.single_contact_layout, parent, false
        )
        return ContactsViewHolder(binding)
    }

    override fun getItemCount(): Int  = contacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val item = contacts[position]
        holder.bind(createOnClick(item), item)
    }

    inner class ContactsViewHolder(
            val binding: SingleContactLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: ContactsModel) {
            binding.item = item
            binding.clicklistener = listener
            binding.executePendingBindings()
        }
    }

    private fun createOnClick(item: ContactsModel): View.OnClickListener {
        return View.OnClickListener {
            onClick(item)
        }
    }
}