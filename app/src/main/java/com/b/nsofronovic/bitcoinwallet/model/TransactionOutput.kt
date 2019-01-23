package com.b.nsofronovic.bitcoinwallet.model

data class TransactionOutput(
    val addresses: List<String>,
    val script: String,
    val script_type: String,
    val spent_by: String,
    val value: Long
)