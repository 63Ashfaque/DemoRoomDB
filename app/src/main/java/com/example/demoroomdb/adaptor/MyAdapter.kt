package com.example.demoroomdb.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoroomdb.databinding.ItemUserCardViewBinding
import com.example.demoroomdb.model.UserItem

class MyAdapter(private val items: List<UserItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val mBinding = ItemUserCardViewBinding.inflate(inflater, parent, false)
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val mBinding: ItemUserCardViewBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(item: UserItem) {
            mBinding.itemVariable = item
            mBinding.executePendingBindings()
        }
    }
}