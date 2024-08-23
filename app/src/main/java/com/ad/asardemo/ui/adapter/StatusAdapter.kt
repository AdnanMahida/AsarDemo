package com.ad.asardemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ad.asardemo.R
import com.ad.asardemo.databinding.ItemCryptoStatusBinding
import com.ad.asardemo.model.Crypto
import com.ad.asardemo.model.Sports
import com.ad.asardemo.model.Status
import com.ad.asardemo.util.setSpannableTextView

class StatusAdapter :
    RecyclerView.Adapter<StatusAdapter.ViewHolder>() {
    private val items: MutableList<Status> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCryptoStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemCryptoStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Status) {
            binding.ivLogo.setImageResource(item.iconResId)
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.title

            when (item.type) {
                is Crypto -> {
                    binding.tvDescription.setSpannableTextView(
                        "${item.type.amount}     +${item.type.upAmount}",
                        "+${item.type.upAmount}",
                        R.color.green,
                        onSpan1Click = {}
                    )
                }

                is Sports -> {
                    binding.tvDescription.text = item.type.description
                }
            }
        }
    }

    fun submitData(items: List<Status>) {
        this.items.addAll(items)
        notifyItemRangeInserted(0, items.size)
    }
}
