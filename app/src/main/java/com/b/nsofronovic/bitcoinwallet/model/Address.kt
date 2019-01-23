package com.b.nsofronovic.bitcoinwallet.model

data class Address(
    val address: String,
    val balance: Int,
    val final_balance: Int,
    val final_n_tx: Int,
    val n_tx: Int,
    val total_received: Int,
    val total_sent: Int,
    val txrefs: List<Transaction>,
    val unconfirmed_balance: Int,
    val unconfirmed_n_tx: Int
)