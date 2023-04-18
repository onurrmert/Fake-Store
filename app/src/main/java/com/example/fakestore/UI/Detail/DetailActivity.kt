package com.example.fakestore.UI.Detail

import android.annotation.SuppressLint
import android.app.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fakestore.Data.remote.Model.StoreModelItem
import com.example.fakestore.R
import com.example.fakestore.Util.Extension.Companion.sendNotification
import com.example.fakestore.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(DetailViewModel::class.java)
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar.apply {
            this?.title = "Product Detail"
        }

        viewModel.getOneData(getID())
        getData()

        binding.addCartBtn.setOnClickListener {
            alert()
        }
    }

    private fun alert(){
        AlertDialog.Builder(this@DetailActivity).apply {
            this.setMessage("Do you want to add product to cart?")
            this.setPositiveButton("Yes", { dialog, which ->
                viewModel.insert(getID())
                this@DetailActivity.sendNotification(this@DetailActivity.resources, "Product added to cart")
            })
        }.show()
    }

    private fun getID() : Int{
        return intent.getIntExtra("id", 0)
    }

    private fun getData(){
        viewModel.storeModelItem.observe(this, { item->
            if (item != null){
                initUI(item)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun initUI(item : StoreModelItem){
        Glide.with(this)
            .load(item.image)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.imageView2)
        binding.textPrice.setText("Price: ${item.price.toString()}$")
        binding.textDescription.setText(item.description.toString())
        binding.textTitle.setText(item.title)
    }
}


