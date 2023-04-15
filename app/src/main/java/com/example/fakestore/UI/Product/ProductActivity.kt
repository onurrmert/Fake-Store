package com.example.fakestore.UI.Product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fakestore.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}