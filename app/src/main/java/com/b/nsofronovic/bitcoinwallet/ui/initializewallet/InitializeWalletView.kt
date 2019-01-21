package com.b.nsofronovic.bitcoinwallet.ui.initializewallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.b.nsofronovic.bitcoinwallet.R
import kotlinx.android.synthetic.main.fragment_initialize_wallet.view.*

class InitializeWalletView : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_initialize_wallet, container, false)

        view.btnCreateWallet.setOnClickListener {
            openWalletNameView()
        }

        return view
    }

    private fun openWalletNameView() {
        findNavController().navigate(R.id.action_initializeWalletView_to_walletNameView)
    }
}