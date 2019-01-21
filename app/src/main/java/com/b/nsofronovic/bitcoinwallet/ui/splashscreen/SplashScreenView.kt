package com.b.nsofronovic.bitcoinwallet.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import javax.inject.Inject

class SplashScreenView : Fragment() {

    private lateinit var viewModel: SplashScreenViewModel

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel::class.java)

        Handler().postDelayed({
            if (!viewModel.isWalletCreated()!!) {
                findNavController().navigate(SplashScreenViewDirections.actionSplashScreenToInitializeWalletView())
            } else {
                findNavController().navigate(SplashScreenViewDirections.actionSplashScreenViewToDashboardView())
            }
        }, 3000)

    }
}