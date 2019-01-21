package com.b.nsofronovic.bitcoinwallet.repository

import androidx.lifecycle.LiveData
import com.b.nsofronovic.bitcoinwallet.database.dao.IContactDao
import com.b.nsofronovic.bitcoinwallet.model.Contact
import io.reactivex.Single
import javax.inject.Inject

class ContactRepository @Inject constructor(IContactDao: IContactDao) {

    private var mIContactDao: IContactDao = IContactDao

    fun getContacts(): LiveData<List<Contact>> {
        return mIContactDao.getAll();
    }

    fun getContact(id: Int): LiveData<Contact> {
        return mIContactDao.findById(id)
    }

    fun insertContact(contact: Contact) {
        mIContactDao.insert(contact)
    }

    fun updateContact(contact: Contact) {
        mIContactDao.update(contact)
    }

    fun deleteContact(contact: Contact) {
        mIContactDao.delete(contact)
    }
}
