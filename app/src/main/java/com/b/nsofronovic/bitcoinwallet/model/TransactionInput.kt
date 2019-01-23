package com.b.nsofronovic.bitcoinwallet.model

data class TransactionInput(
    val age: Int,
    val output_index: Int,
    val output_value: Long,
    val prev_hash: String,
    val script_type: String,
    val sequence: Long,
    val witness: List<String>
)