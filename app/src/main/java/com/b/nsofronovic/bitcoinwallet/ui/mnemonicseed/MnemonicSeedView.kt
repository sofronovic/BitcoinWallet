package com.b.nsofronovic.bitcoinwallet.ui.mnemonicseed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.params.TestNet3Params
import org.bitcoinj.wallet.Wallet
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mnemonic_seed.*
import kotlinx.android.synthetic.main.fragment_mnemonic_seed.view.*
import org.reactivestreams.Subscription

class MnemonicSeedView : Fragment() {

    private lateinit var viewModel: MnemonicSeedViewModel

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_mnemonic_seed, container, false)

        view.btnNextStep.setOnClickListener {
            openAuthenticationSetupView()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MnemonicSeedViewModel::class.java)

        getMnemonicPhrase()
    }

    private fun openAuthenticationSetupView() {
        findNavController().navigate(R.id.action_mnemonicSeedView_to_authenticationSetupView)
    }

    private fun getMnemonicPhrase() {
        showMnemonicPhrase(viewModel.getMnemonicPhrase())
    }

    private fun showMnemonicPhrase(phrase: String?) {
        tvMnemonicPhrase.text = phrase
    }
}