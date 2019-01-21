package com.b.nsofronovic.bitcoinwallet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey val id: Int?,
    @ColumnInfo val name: String,
    @ColumnInfo val address: String
)
