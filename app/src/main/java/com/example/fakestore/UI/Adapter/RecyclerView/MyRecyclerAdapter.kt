package com.example.fakestore.UI.Adapter.RecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.Data.Model.StoreModel
import com.example.fakestore.R
import com.example.fakestore.databinding.RecyclerRowsBinding

class MyRecyclerAdapter (
    private val storeModel: StoreModel,
    private val itemClick: IOnItemClick
    ): RecyclerView.Adapter<MyRecyclerAdapter.RecyclerViewHolder>(){

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = RecyclerRowsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_rows, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return storeModel.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.binding.textPrice.text = "Price: ${storeModel.get(position).price.toString()}$"
        holder.binding.textTitle.setText(storeModel.get(position).title)

        Glide.with(holder.itemView.context)
            .load(storeModel.get(position).image)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imageView)

        holder.binding.imageView.setOnClickListener {
            itemClick.itemClick(storeModel.get(position).id!!)
        }
    }
}