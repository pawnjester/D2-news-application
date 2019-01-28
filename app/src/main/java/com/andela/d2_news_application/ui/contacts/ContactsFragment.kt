package com.andela.d2_news_application.ui.contacts


import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.andela.d2_news_application.R
import com.andela.d2_news_application.adapter.ContactsAdapter
import com.andela.d2_news_application.databinding.FragmentContactsBinding
import com.andela.d2_news_application.model.ContactsModel


/**
 * A simple [Fragment] subclass.
 *
 */
class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding

    private val listAdapter by lazy {
        ContactsAdapter()
    }

    val PERMISSIONS_REQUEST_READ_CONTACTS = 200

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

        listAdapter.updateList(accessContacts())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                accessContacts();
            } else {
                Toast.makeText(activity!!,
                        "Until you grant the permission, we canot display the names",
                        Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun accessContacts(): List<ContactsModel> {

        val listOfContacts = ArrayList<ContactsModel>()
        val cursor = activity!!
                .contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null)

        cursor?.let {
            while (it.moveToNext()) {
                val contactsId = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                val name = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNumber = it.getString(it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                val contacts = ContactsModel().apply {
                    contactId = contactsId
                    contactNumber  = phoneNumber
                    contactName  = name
                }

                listOfContacts.add(contacts)

            }
        }

        cursor?.close()

        return listOfContacts
    }





}
