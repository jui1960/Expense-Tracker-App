package com.example.expensetracker

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.databinding.ItemBinding

class ExpenseAdapter(
    private val list: List<Data>,
    private val onEdit: (Data) -> Unit,
    private val onDelete: (Data) -> Unit


) : RecyclerView.Adapter<ExpenseAdapter.viewHolder>() {
    inner class viewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: viewHolder,
        position: Int
    ) {
        val data = list[position]
        holder.binding.tvDisplayTitle.text = data.title
        holder.binding.tvDisplayDate.text = data.date
        holder.binding.tvDisplayAmount.text = "-$ ${data.amount}"
        holder.binding.btnEdit.setOnClickListener {
            onEdit(data)
        }
        holder.binding.btnDelete.setOnClickListener {
            onDelete(data)
        }


        //item details
        holder.binding.tvDisplayTitle.setOnClickListener {
            val context = holder.binding.tvDisplayTitle.context
            val intent = Intent(context, ItemDetails::class.java)
            intent.putExtra("id",data.id)
            intent.putExtra("title",data.title)
            intent.putExtra("amount",data.amount)
            intent.putExtra("date",data.date)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}