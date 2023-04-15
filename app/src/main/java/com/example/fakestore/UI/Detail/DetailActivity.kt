package com.example.fakestore.UI.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fakestore.Data.Model.StoreModelItem
import com.example.fakestore.R
import com.example.fakestore.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getOneData(getID())
        getData()
    }

    private fun getID() : Int{
        return intent.getIntExtra("id", 0)
    }

    private fun getData(){
        viewModel.storeModelItem.observe(this, {
            item->
            initUI(item)
        })
    }

    private fun initUI(item : StoreModelItem){
        Glide.with(this)
            .load(item.image)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.imageView)

        binding.textPrice.setText(item.price.toString())
    }
}