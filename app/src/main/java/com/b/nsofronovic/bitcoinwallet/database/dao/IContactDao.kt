package com.b.nsofronovic.bitcoinwallet.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.b.nsofronovic.bitcoinwallet.model.Contact

@Dao
interface IContactDao {

    @Query("SELECT * FROM contact")
    fun getAll(): LiveData<List<Contact>>

    @Query("SELECT * FROM contact WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<Contact>

    @Insert
    fun insert(vararg contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Update
    fun update(contact: Contact)
}