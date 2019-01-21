package com.b.nsofronovic.bitcoinwallet.ui.contactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b.nsofronovic.bitcoinwallet.model.Contact
import com.b.nsofronovic.bitcoinwallet.repository.ContactRepository
import io.reactivex.Completable
import javax.inject.Inject

class ContactsViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {

    fun getContacts(): LiveData<List<Contact>> {
        return repository.getContacts()
    }

    fun deleteContact(contact: Contact): Completable {
        return Completable.fromAction{
            repository.deleteContact(contact)
        }
    }

}