package com.b.nsofronovic.bitcoinwallet.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.api.BlockchainApi
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.model.Wallet
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import org.bitcoinj.core.Coin
import java.lang.Exception
import javax.inject.Inject

class DashboardView : Fragment() {

    private lateinit var viewModel: DashboardViewModel

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    val blockchainApi by lazy {
        BlockchainApi.create()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        view.btnContacts.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardView_to_contactsView)
        }

        view.btnSendBitcoin.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardView_to_sendTransactionView)
        }

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
                { result -> tvBalance.text = (result.balance.toString())},
                { error -> Log.d("AddressBalance", error.message) }
            ))


    }
}