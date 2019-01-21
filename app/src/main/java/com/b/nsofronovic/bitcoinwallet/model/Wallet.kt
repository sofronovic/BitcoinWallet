package com.b.nsofronovic.bitcoinwallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wallet(
    @PrimaryKey val id: Int?,
    val address: String,
    val name: String,
    val authenticationType: AuthenticationType)