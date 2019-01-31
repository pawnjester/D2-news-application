package com.andela.d2_news_application.ui.contacts


import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Build
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
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.telephony.SmsManager
import android.util.Log
import com.andela.d2_news_application.utils.InjectorUtils
import com.andela.d2_news_application.viewModel.SharedViewModel
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.provider.ContactsContract.CommonDataKinds.Phone






/**
 * A simple [Fragment] subclass.
 *
 */
class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding

    private lateinit var viewModel: SharedViewModel

    val PERMISSIONS_REQUEST_READ_CONTACTS = 200

    private val listAdapter by lazy {
        ContactsAdapter({
            viewModel.contactItem = it
            shareMessage(viewModel.contactItem?.contactName?:"",
                    viewModel.homeItem?.url?: "")
        })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_contacts, container, false
        )
        requestContacts()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    companion object {
        fun newInstance() = ContactsFragment()
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

    private fun requestContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                activity?.checkSelfPermission(
                        android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS),
                    PERMISSIONS_REQUEST_READ_CONTACTS)
        } else {
            displayContacts()
        }
    }

    private fun shareMessage(person: String, message: String) {
        Log.e("make", "sense")
        Intent(android.content.Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here")
            putExtra(android.content.Intent.EXTRA_TEXT,
                    "${person} wants you to view this message: ${message}")
            startActivity(this)
        }
    }

    private fun initViewModel() {
        val factory = InjectorUtils
                .provideSharedViewModelFactory(context!!)
        viewModel = ViewModelProviders
                .of(activity!!, factory).get(SharedViewModel::class.java)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayContacts()
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
                null, null, null,
                null)

        cursor?.let {
            while (it.moveToNext()) {
                val contactsId =
                        it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID))
                val name =
                        it.getString(it.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val hasphone =
                        it.getString(it.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER))

                if (hasphone.equals("1", ignoreCase = true)) {
                    val phones = activity!!.contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                    + " = " + contactsId,
                            null, null
                    )

                    while (phones.moveToNext()) {
                        val number = phones.getString(phones.getColumnIndex(Phone.NUMBER))
                        val type = phones.getInt(phones.getColumnIndex(Phone.TYPE))
                        when (type) {
                            Phone.TYPE_HOME -> {
                                val contacts = ContactsModel().apply {
                                    contactId = contactsId
                                    contactNumber  = number
                                    contactName  = name
                                }
                                listOfContacts.add(contacts)
                            }
                            Phone.TYPE_MOBILE -> {
                                val contacts = ContactsModel().apply {
                                    contactId = contactsId
                                    contactNumber  = number
                                    contactName  = name
                                }
                                listOfContacts.add(contacts)
                            }
                            Phone.TYPE_WORK -> {
                                val contacts = ContactsModel().apply {
                                    contactId = contactsId
                                    contactNumber  = number
                                    contactName  = name
                                }
                                listOfContacts.add(contacts)
                            }
                        }
                    }
                    phones?.close()
                }
            }
        }

        cursor?.close()

        return listOfContacts
    }





}
