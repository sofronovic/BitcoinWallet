package com.b.nsofronovic.bitcoinwallet.ui.walletoverview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.model.WalletWizard
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_wallet_overview.*
import kotlinx.android.synthetic.main.fragment_wallet_overview.view.*
import javax.inject.Inject

class WalletOverviewView : Fragment() {

    private lateinit var viewModel: WalletOverviewViewModel

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wallet_overview, container, false)

        view.btnFinishSetup.setOnClickListener {
            finishWalletSetup()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WalletOverviewViewModel::class.java)

        viewModel.getWalletAddress()
        displayWallet(viewModel.getWalletWizard())
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun displayWallet(walletWizard: WalletWizard) {
        tvOverviewAddress.text = walletWizard.address
        tvOverviewName.text = walletWizard.name
        tvOverviewAuthentication.text = walletWizard.authenticationType.toString()
    }

    private fun finishWalletSetup() {
        compositeDisposable.add(
            viewModel.createWallet()
                .subscribeOn(Schedulers.io())
                .doOnError {
                    Log.d("WalletInit", "Error while creating wallet", it.cause)
                }
                .subscribe {
                    setWalletCreated()
                    setWalletName(tvOverviewName.text.toString())
                    openDashboard()
                })

    }

    private fun setWalletCreated() {
        viewModel.setWalletCreated()
    }

    private fun setWalletName(walletName: String) {
        viewModel.setWalletName(walletName)
    }

    private fun openDashboard() {
        findNavController().navigate(R.id.action_walletOverview_to_dashboardView)
    }
}