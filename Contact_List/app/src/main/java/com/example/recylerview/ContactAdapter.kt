package com.example.recylerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    private val list: MutableList<Contact> = mutableListOf()

    class  ContactAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val Name: TextView = itemView.findViewById(R.id.name)
        private val Phone: TextView = itemView.findViewById(R.id.phone)
        private val Photograph: TextView = itemView.findViewById(R.id.photograph)

        fun bind(contact: Contact){
            Name.text = contact.name
            Phone.text = contact.phone
        }
    }

    fun updateList(list: List<Contact>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return  ContactAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}