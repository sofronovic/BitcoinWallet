package com.b.nsofronovic.bitcoinwallet.model

data class WalletWizard (
    var address: String? = null,
    var name: String? = null,
    var authenticationType: AuthenticationType? = null
)