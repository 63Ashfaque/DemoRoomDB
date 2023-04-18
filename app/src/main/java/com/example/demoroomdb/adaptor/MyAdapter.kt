package com.example.demoroomdb.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoroomdb.databinding.ItemUserCardViewBinding
import com.example.demoroomdb.roomdb.Contact

class MyAdapter(private var items: List<Contact>, private val clickListener: MyClickListener) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val mBinding = ItemUserCardViewBinding.inflate(inflater, parent, false)
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item,clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(private val itemBinding: ItemUserCardViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Contact, clickListener1: MyClickListener) {
            itemBinding.itemVariable = item
            itemBinding.clickListener = clickListener1
            itemBinding.executePendingBindings()
        }

    }

    interface MyClickListener {
        fun onUpdateItemClick(item: Contact)
        fun onDeleteItemClick(item: Contact)
    }


}