package com.example.fakestore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fakestore.UI.Adapter.ViewPagerAdapter
import com.example.fakestore.UI.Electronics.ElectronicsFragment
import com.example.fakestore.UI.Jewelery.JeweleryFragment
import com.example.fakestore.UI.Mens.MensFragment
import com.example.fakestore.UI.Womens.WomensFragment
import com.example.fakestore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = ViewPagerAdapter(supportFragmentManager)

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.addFragment(ElectronicsFragment(), "Electronics")
        adapter.addFragment(JeweleryFragment(), "")
        adapter.addFragment(MensFragment(), "Mens")
        adapter.addFragment(WomensFragment(), "Womens")

        binding.viewPager.adapter = adapter

        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.getTabAt(0)!!.setText("Electronics")
        binding.tabLayout.getTabAt(1)!!.setText("Jewelery")
        binding.tabLayout.getTabAt(2)!!.setText("Mens")
        binding.tabLayout.getTabAt(3)!!.setText("Womens")
    }
}