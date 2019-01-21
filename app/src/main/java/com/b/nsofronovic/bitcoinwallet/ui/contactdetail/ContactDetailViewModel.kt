package com.b.nsofronovic.bitcoinwallet.ui.contactdetail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.model.Contact
import com.b.nsofronovic.bitcoinwallet.repository.ContactRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ContactDetailViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {

    @SuppressLint("CheckResult")
    fun insertContact(contact: Contact) {
        repository.insertContact(contact)
    }

    fun updateContact(contact: Contact) {
        repository.updateContact(contact)
    }

    fun getContact(id: Int): LiveData<Contact> {
        return repository.getContact(id)
    }


}