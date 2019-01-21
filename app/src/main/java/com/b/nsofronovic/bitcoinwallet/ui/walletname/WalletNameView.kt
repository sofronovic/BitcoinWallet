package com.b.nsofronovic.bitcoinwallet.ui.walletname

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
import com.b.nsofronovic.bitcoinwallet.rx.RxBus
import com.b.nsofronovic.bitcoinwallet.rx.RxEvent
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_wallet_name.*
import kotlinx.android.synthetic.main.fragment_wallet_name.view.*
import javax.inject.Inject

class WalletNameView : Fragment() {

    private lateinit var viewModel: WalletNameViewModel

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wallet_name, container, false)

        view.btnSubmitWalletName
            .setOnClickListener {
                val walletName = etWalletName.text.toString()
                storeWalletName(walletName)
                startWalletSetup(walletName)
            }

        compositeDisposable.add(RxBus.listen(RxEvent.DownloadPercentage::class.java).subscribe {
            fillProgressBar(it.double)
        })

        compositeDisposable.add(RxBus.listen(RxEvent.DownloadStatus::class.java).subscribe() {
            if (it.isFinished) {
                onDownloadFinished()
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WalletNameViewModel::class.java)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun storeWalletName(walletName: String) {
        viewModel.storeWalletNameInWizard(walletName)
    }

    private fun openMnemonicPhraseScreen() {
        findNavController().navigate(R.id.action_walletNameView_to_mnemonicSeedView)
    }

    private fun startWalletSetup(walletName: String) {
        viewModel.setupWallet(walletName)
    }

    private fun fillProgressBar(percentage: Double) {
        Log.d("DownloadManager", percentage.toString())
    }

    private fun onDownloadFinished() {
        Log.d("DownloadManager", "Download finished")
        openMnemonicPhraseScreen()
    }

}