package com.b.nsofronovic.bitcoinwallet.ui.contactlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.model.Contact
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import javax.inject.Inject

class ContactsView : Fragment(), IContactListener {

    private lateinit var viewModel: ContactsViewModel

    private val compositeDisposable = CompositeDisposable()

    private lateinit var adapter: ContactAdapter

    private var contacts: List<Contact> = mutableListOf()

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)

        view.rvContacts.layoutManager = LinearLayoutManager(activity!!)
        adapter = ContactAdapter(this.activity!!, this)
        view.rvContacts.adapter = adapter

        view.rvContacts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                when {
                    dy > 0 -> view.fabNewContact.collapse()
                    else -> view.fabNewContact.expand()
                }
            }
        })

        val swipeHandler = object : SwipeToDeleteCallback(activity!!, this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
                val adapter = view.rvContacts.adapter as ContactAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(view.rvContacts)

        view.fabNewContact.setOnClickListener {
            openContactDetail()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactsViewModel::class.java)

        getContacts()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun getContacts() {
        viewModel.getContacts().observe(this, Observer { contactList ->
            contacts = contactList
            adapter.setContacts(contacts)
        })
    }

    private fun openContactDetail() {
        findNavController().navigate(R.id.action_contactsView_to_contactDetailView)
    }

    override fun onContactClicked(id: Int?) {
        val action = ContactsViewDirections.actionContactsViewToContactDetailView(id!!)
        action.contactId = id
        findNavController().navigate(action)
    }

    override fun onContactSwiped(position: Int) {
        compositeDisposable.add(
            viewModel.deleteContact(contacts[position])
                .subscribeOn(Schedulers.io())
                .doOnError {
                    Log.d("Error", "Error while deleting contact", it.cause)
                }
                .subscribe {
                    showSnackbar()
                })
    }

    private fun showSnackbar() {
        Snackbar.make(this.view?.rootView!!, "Contact successfully removed", Snackbar.LENGTH_SHORT).show()
    }
}