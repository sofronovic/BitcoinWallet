package com.b.nsofronovic.bitcoinwallet.ui.transactiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.api.BlockchainApi
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import javax.inject.Inject

class TransactionDetailView : Fragment() {

    private lateinit var viewModel: TransactionDetailViewModel

    @Inject
    lateinit var customViewModelFactory: CustomViewModelFactory

    val blockchainApi by lazy {
        BlockchainApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, customViewModelFactory).get(TransactionDetailViewModel::class.java)

        if (arguments != null) {
            val txHash = TransactionDetailViewArgs.fromBundle(arguments!!).transactionHash
                getTransaction(txHash)
        }
    }

    private fun getTransaction(hash: String) {
        blockchainApi.getTransaction(hash)
    }
}