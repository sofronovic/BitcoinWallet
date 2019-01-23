package com.b.nsofronovic.bitcoinwallet.ui.receivetransaction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.application.App
import com.b.nsofronovic.bitcoinwallet.ui.CustomViewModelFactory
import com.google.zxing.WriterException
import com.google.zxing.qrcode.encoder.QRCode
import kotlinx.android.synthetic.main.fragment_receive_transaction.*
import javax.inject.Inject

class ReceiveTransactionView : Fragment() {

    private lateinit var viewModel: ReceiveTransactionViewModel

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.applicationComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_receive_transaction, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReceiveTransactionViewModel::class.java)

        if (arguments != null) {
            val address = ReceiveTransactionViewArgs.fromBundle(arguments!!).myAddress
            tvReceiveAddress.setText(address)
            generateQrCode(address)
        }
    }

    private fun generateQrCode(address: String) {
        val qrEncoder = QRGEncoder(address, null, QRGContents.Type.TEXT, 200)
        try {
            val bitmap = qrEncoder.encodeAsBitmap()
            ivQrCode.setImageBitmap(bitmap)
        } catch (ex: WriterException) {
            Log.e("Exception", ex.message)
        }
    }
}