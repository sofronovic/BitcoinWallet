package com.b.nsofronovic.bitcoinwallet.ui.sendtransaction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import com.google.android.material.snackbar.Snackbar
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_send_transaction.*
import kotlinx.android.synthetic.main.fragment_send_transaction.view.*
import org.bitcoinj.core.InsufficientMoneyException
import javax.inject.Inject

class SendTransactionView : Fragment() {

    private lateinit var viewModel: SendTransactionViewModel

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_send_transaction, container, false)

        view.btnSendTransaction.setOnClickListener {

        viewModel.sendTransaction(etBitcoinAmount.text.toString(), etReceiveAddress.text.toString())
            .subscribe(object : SingleObserver<Boolean> {
                override fun onSuccess(isSuccess: Boolean?) {
                    if (isSuccess!!) {
                        Snackbar.make(view, "Transaction successfully sent", Snackbar.LENGTH_SHORT)
                    }
                }

                override fun onError(e: Throwable?) {
                    Snackbar.make(view, e?.message!!, Snackbar.LENGTH_SHORT)
                }

                override fun onSubscribe(d: Disposable?) {
                    Log.d("SendTransaction", "onSubscribe();")
                }
            })
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SendTransactionViewModel::class.java)
    }
}