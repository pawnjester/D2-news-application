package com.andela.d2_news_application.ui.contacts


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.andela.d2_news_application.R
import com.andela.d2_news_application.adapter.ContactsAdapter
import com.andela.d2_news_application.databinding.FragmentContactsBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding

    private val listAdapter by lazy {
        ContactsAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_contacts, container, false
        )
        return binding.root
    }


    companion object {
        fun newInstance() = ContactsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayContacts()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun displayContacts() {
        with(binding.contactsRecyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false)
            adapter = listAdapter
        }

//        listAdapter.
    }

//    private fun accessContacts() {
//        val cursor = activity!!
//                .contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
//                null, null, null, null)
//
//        if (cursor.count > 0){
//
//        }
//        cursor.close()
//    }





}
