package com.b.nsofronovic.bitcoinwallet.ui.contactlist

interface   IContactListener {

    fun onContactClicked(id: Int?)

    fun onContactSwiped(position: Int)
}