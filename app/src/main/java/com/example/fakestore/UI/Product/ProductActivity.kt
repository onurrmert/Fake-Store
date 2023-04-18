package com.example.fakestore.UI.Product

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fakestore.UI.Adapter.RecyclerView.IOnItemClick
import com.example.fakestore.Util.Extension.Companion.sendNotification
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
                viewModel.storeEntity.observe(this@ProductActivity,{item1 ->
                    binding.recyclerView.layoutManager = GridLayoutManager(this@ProductActivity, 2)
                    binding.recyclerView.adapter = ProductRecyclerAdapter(item, item1,
                        object : IOnItemClick{
                            override fun itemClick(id: Int) {
                                delete(id)
                            }
                        })
                })
            }else{
                visibleAnim()
                this@ProductActivity.toast("No data found")
            }
        })
    }

    private fun delete(id : Int) {
        AlertDialog.Builder(this@ProductActivity).apply {
            this.setMessage("Do you want delete?")
            this.setPositiveButton("Yes", { dialog, which ->
                viewModel.delete(id)
                this@ProductActivity.sendNotification(resources, "Delete product!!!")
            })
        }.show()
    }

    private fun visibleAnim(){
        binding.animateToStart.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    private fun goneAnim(){
        binding.animateToStart.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finish()
    }
}