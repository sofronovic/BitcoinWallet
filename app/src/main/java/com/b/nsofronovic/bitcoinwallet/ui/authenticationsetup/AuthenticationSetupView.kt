package com.b.nsofronovic.bitcoinwallet.ui.authenticationsetup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.model.AuthenticationType
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_authentication_setup.*
import kotlinx.android.synthetic.main.fragment_authentication_setup.view.*
import javax.inject.Inject

class AuthenticationSetupView : Fragment() {

    private lateinit var viewModel: AuthenticationSetupViewModel

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_authentication_setup, container, false)

        view.btnInitializeWallet.setOnClickListener {
            openWalletOverview()
        }

        view.rgAuthentication.setOnCheckedChangeListener { _, checkedId ->
            onRadioButtonChecked(checkedId)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthenticationSetupViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun onRadioButtonChecked(checkedId: Int) {
        when (checkedId) {
            rbPin.id -> {
                viewModel.setAuthentication(AuthenticationType.PIN)
            }

            rbFingerprint.id -> {
                viewModel.setAuthentication(AuthenticationType.FINGERPRINT)
            }
        }
    }

    private fun openWalletOverview() {
        findNavController().navigate(R.id.action_authenticationSetupView_to_walletOverview2)
    }
}
