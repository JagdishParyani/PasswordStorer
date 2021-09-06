package com.example.passwordstorer.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordstorer.databinding.ItemDashboardAdapterBinding
import com.example.passwordstorer.room.entity.CategoryEntity
import java.util.*

class DashBoardAdapter(
    private val context: Context,
    private val categoryList: ArrayList<CategoryEntity>
) :
    RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val binding: ItemDashboardAdapterBinding = ItemDashboardAdapterBinding.inflate(
            LayoutInflater.from(context)
        )
        return DashBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        holder.bind(categoryList[position], position)
    }

    override fun getItemCount(): Int = categoryList.size

    fun updateData(categoriesList: List<CategoryEntity>) {
        if (categoriesList.isNotEmpty()) {
            this.categoryList.clear()
            this.categoryList.addAll(categoriesList)
            notifyDataSetChanged()
        }
    }

    inner class DashBoardViewHolder(private val binding: ItemDashboardAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryEntity, position: Int) {
            binding.tvCategory.text = category.categoryName
        }

    }
}