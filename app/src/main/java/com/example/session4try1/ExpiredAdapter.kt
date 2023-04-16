package com.example.session4try1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session4try1.databinding.ExpiredItemBinding

class ExpiredViewHolder(var binding: ExpiredItemBinding) : RecyclerView.ViewHolder(binding.root)
class ExpiredAdapter(val list: List<ModelTask>) : RecyclerView.Adapter<ExpiredViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpiredViewHolder {
        return ExpiredViewHolder(ExpiredItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ExpiredViewHolder, position: Int) {
        holder.binding
    }

}