package com.b.nsofronovic.bitcoinwallet.api

import android.provider.Telephony.TextBasedSmsColumns.BODY
import com.b.nsofronovic.bitcoinwallet.model.Address
import com.b.nsofronovic.bitcoinwallet.model.Transaction
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.bitcoinj.core.Block
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface BlockchainApi {

    @GET("v1/btc/test3/addrs/{ADDRESS}")
    fun getAddressInfo(@Path("ADDRESS") address: String): Observable<Address>

    @GET("v1/btc/test3/txs/{TRANSACTION}")
    fun getTransaction(@Path("TRANSACTION") transaction: String): Observable<Transaction>

    companion object {

        private val logging = HttpLoggingInterceptor()
        private val httpClient = OkHttpClient.Builder()

        fun create(): BlockchainApi {
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                ).addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.blockcypher.com/")
                .client(httpClient.build())
                .build()

            return retrofit.create(BlockchainApi::class.java)
        }
    }
}