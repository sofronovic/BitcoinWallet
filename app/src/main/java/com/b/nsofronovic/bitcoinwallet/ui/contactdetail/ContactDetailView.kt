package com.b.nsofronovic.bitcoinwallet.ui.contactdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.model.Contact
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_contact_detail.*
import kotlinx.android.synthetic.main.fragment_contact_detail.view.*
import java.lang.Exception
import javax.inject.Inject

class ContactDetailView : Fragment() {

    private lateinit var viewModel: ContactDetailViewModel

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private var isEditMode: Boolean = false

    private var currentContactId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_detail, container, false)

        view.btnAddContact.setOnClickListener {
            if (isEditMode) {
                addNewContact(
                    Contact(
                        currentContactId,
                        etContactName.text.toString(),
                        etContactAddress.text.toString()
                    )
                )
            } else {
                addNewContact(Contact(null, etContactName.text.toString(), etContactAddress.text.toString()))
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactDetailViewModel::class.java)

        if (arguments != null) {
            val id: Int = ContactDetailViewArgs.fromBundle(arguments!!).contactId
            getContact(id)
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun getContact(id: Int) {
        viewModel.getContact(id).observe(this, Observer { contact ->
            currentContactId = id
            isEditMode = true
            updateTextFields(contact)
        })
    }

    private fun updateTextFields(contact: Contact) {
        etContactName.setText(contact.name)
        etContactAddress.setText(contact.address)
    }

    private fun addNewContact(contact: Contact) {
        if (isEditMode) {
            compositeDisposable.add(
                Completable.fromCallable {
                    Runnable {
                        viewModel.updateContact(contact)
                    }.run()
                }.doOnError {
                    Log.d("ROOM", "Error occurred while adding contact to db.")
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        openContactListView()
                    })
        } else {
            compositeDisposable.add(
                Completable.fromCallable {
                    Runnable {
                        viewModel.insertContact(contact)
                    }.run()
                }.doOnError {
                    Log.d("ROOM", "Error occurred while adding contact to db.")
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe {
                        openContactListView()
                    }
            )
        }
    }

    private fun openContactListView() {
        findNavController().navigate(R.id.action_contactDetailView_to_contactsView)
    }
}