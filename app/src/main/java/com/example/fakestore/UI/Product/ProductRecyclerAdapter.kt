package com.example.fakestore.UI.Product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.Data.remote.Model.StoreModelItem
import com.example.fakestore.R
import com.example.fakestore.databinding.RecyclerRowsBinding

class ProductRecyclerAdapter (
    private val storeModelItemList: List<StoreModelItem>
        ) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>(){

    class ProductViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = RecyclerRowsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return  ProductViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.product_recycler_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return storeModelItemList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.textTitle.setText(storeModelItemList.get(position).title)
        holder.binding.textPrice.text = "Price: ${storeModelItemList.get(position).price}"

        Glide.with(holder.itemView.context)
            .load(storeModelItemList.get(position).image)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imageView)
    }
}