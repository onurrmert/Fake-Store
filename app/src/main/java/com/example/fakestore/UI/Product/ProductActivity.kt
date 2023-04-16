package com.example.fakestore.UI.Product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fakestore.Util.Extension.Companion.toast
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
        supportActionBar.apply {
            this?.title = "Product List"
        }
        getData()
    }

    private fun getData(){
        viewModel.storeModelItemList.observe(this@ProductActivity, { item->
            if (item.size > 0){
                goneAnim()
                binding.recyclerView.layoutManager = GridLayoutManager(this@ProductActivity, 2)
                binding.recyclerView.adapter = ProductRecyclerAdapter(item.reversed())
            }else{
                visibleAnim()
                this@ProductActivity.toast("No data found")
            }
        })
    }

    private fun visibleAnim(){
        binding.animateToStart.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    private fun goneAnim(){
        binding.animateToStart.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }
}