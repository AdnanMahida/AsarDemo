package com.ad.asardemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ad.asardemo.databinding.ItemActivityBinding

class ActivityAdapter : RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {
    private val items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            // Bind data to views here
        }
    }

    fun submitData(items: List<String>) {
        this.items.addAll(items)
        notifyItemRangeInserted(0, items.size)
    }
}
