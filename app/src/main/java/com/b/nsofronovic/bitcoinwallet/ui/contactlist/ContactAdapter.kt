package com.b.nsofronovic.bitcoinwallet.ui.contactlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b.nsofronovic.bitcoinwallet.R
import com.b.nsofronovic.bitcoinwallet.model.Contact
import kotlinx.android.synthetic.main.contact_list_item.view.*

class ContactAdapter(val context: Context, private val listener: IContactListener) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var contacts: List<Contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.tvName.text = contact.name
        holder.tvAddress.text = contact.address
        holder.root.setOnClickListener { listener.onContactClicked(contact.id) }
    }

    fun setContacts(contactList: List<Contact>) {
        contacts = contactList
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        contacts.drop(position)
        notifyDataSetChanged()
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tv_contact_list_item_name!!
        val tvAddress = view.tv_contact_list_item_address!!
        val root = view.cvContactItem!!
    }

}