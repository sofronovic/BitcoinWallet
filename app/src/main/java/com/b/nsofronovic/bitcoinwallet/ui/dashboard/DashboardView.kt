package com.b.nsofronovic.bitcoinwallet.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.api.BlockchainApi
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.model.Transaction
import com.b.nsofronovic.bitcoinwallet.model.Wallet
import com.b.nsofronovic.bitcoinwallet.ui.ContainerActivity
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import org.bitcoinj.core.Coin
import javax.inject.Inject

class DashboardView : Fragment(), ITransactionListener {

    private lateinit var viewModel: DashboardViewModel

    private val compositeDisposable = CompositeDisposable()

    private lateinit var adapter: TransactionAdapter

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private var transactions: List<Transaction> = mutableListOf()

    val blockchainApi by lazy {
        BlockchainApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        (activity as ContainerActivity).showNavigation()

        view.rvTransactions.layoutManager = LinearLayoutManager(activity!!)
        adapter = TransactionAdapter(this.activity!!, this)
        view.rvTransactions.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

        compositeDisposable.add(viewModel.loadWalletFromDatabase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t1 -> displayWallet(t1) })

    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun displayWallet(wallet: Wallet) {
        tvName.text = wallet.name
        tvAddress.text = wallet.address

        compositeDisposable.add(blockchainApi.getAddressInfo(wallet.address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    setTransactionHistory(result.txrefs)
                    tvBalance.text = (Coin.valueOf(result.balance.toLong()).toFriendlyString()) },
                { error -> Log.d("Blockchain API", error.message) }
            ))
    }

    private fun setTransactionHistory(txs: List<Transaction>) {
        transactions = txs
        adapter.setTransactions(transactions)
    }

    private fun openReceiveTransactionScreen() {
        val action = DashboardViewDirections
            .actionDashboardViewToReceiveTransaction(tvAddress.text.toString())
        action.myAddress = tvAddress.text.toString()
        findNavController().navigate(action)
    }

    override fun onTransactionClicked(hash: String?) {
        val action =
            DashboardViewDirections.actionDashboardViewToTransactionDetailView(hash!!)
        action.transactionHash = hash
        findNavController().navigate(action)
    }
}