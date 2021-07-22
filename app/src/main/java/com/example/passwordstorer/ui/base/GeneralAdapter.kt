package com.example.passwordstorer.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordstorer.databinding.ItemDashboardAdapterBinding

class GeneralAdapter(private val context: Context, private val categoryList: List<String>) :
    RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralViewHolder {
        val binding: ItemDashboardAdapterBinding = ItemDashboardAdapterBinding.inflate(
            LayoutInflater.from(context)
        )
        return GeneralViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GeneralViewHolder, position: Int) {
        holder.bind(categoryList[position], position)
    }

    override fun getItemCount(): Int = categoryList.size

    inner class GeneralViewHolder(private val binding: ItemDashboardAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: String, position: Int) {
            binding
        }
    }
}