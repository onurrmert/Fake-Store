package com.example.fakestore.UI.Product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fakestore.Data.remote.Model.StoreModelItem
import com.example.fakestore.databinding.ActivityProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProductBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(ProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }

    private fun getData(){
        viewModel.storeModelItemList

    }
}