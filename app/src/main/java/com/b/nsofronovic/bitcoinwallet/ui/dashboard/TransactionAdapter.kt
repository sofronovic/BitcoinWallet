package com.b.nsofronovic.bitcoinwallet.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.model.Transaction
import kotlinx.android.synthetic.main.transaction_list_item.view.*
import org.bitcoinj.core.Coin

class TransactionAdapter(val context: Context, private val listener: ITransactionListener) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private var transactions: List<Transaction> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.transactionHash.text = transaction.tx_hash
        holder.transactionValue.text = Coin.valueOf(transaction.value.toLong()).toFriendlyString()
        holder.confirmationDate.text = transaction.confirmed.split("T")[0]
        holder.root.setOnClickListener { listener.onTransactionClicked(transaction.tx_hash) }
    }

    fun setTransactions(transactionList: List<Transaction>) {
        transactions = transactionList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transactionHash = view.tvTransactionHash!!
        val transactionValue = view.tvTransactionValue!!
        val confirmationDate = view.tvConfirmationDate!!
        val root = view.transactionRootItem!!
    }


}