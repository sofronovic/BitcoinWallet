package com.b.nsofronovic.bitcoinwallet.model

data class Transaction(
    val block_height: Int,
    val confirmations: Int,
    val confirmed: String,
    val double_spend: Boolean,
    val ref_balance: Int,
    val spent: Boolean,
    val tx_hash: String,
    val tx_input_n: Int,
    val tx_output_n: Int,
    val value: Int,
    val inputs: List<TransactionInput>?,
    val outputs: List<TransactionOutput>?
)