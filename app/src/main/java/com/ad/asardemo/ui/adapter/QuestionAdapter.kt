package com.ad.asardemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ad.asardemo.databinding.ItemYesNoBinding
import com.ad.asardemo.model.Question

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    private val items: MutableList<Question> = mutableListOf()
    private var onDetailsClick: (() -> Unit)? = null
    private var onDYesNoClick: ((Type) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemYesNoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemYesNoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Question) {
            binding.ivLogo.setImageResource(item.iconResId)
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.title

            binding.cvCrypto.setOnClickListener {
                onDetailsClick?.invoke()
            }

            binding.btnYes.setOnClickListener {
                onDYesNoClick?.invoke(Type.YES)
            }
            binding.btnNo.setOnClickListener {
                onDYesNoClick?.invoke(Type.NO)
            }
        }
    }

    fun submitData(items: List<Question>) {
        this.items.addAll(items)
        notifyItemRangeInserted(0, items.size)
    }

    fun onDetailsClick(v: () -> Unit) {
        this.onDetailsClick = v
    }

    fun onYesNoClick(v: (type: Type) -> Unit) {
        this.onDYesNoClick = v
    }

    enum class Type {
        YES, NO
    }
}
