package com.example.fakestore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.fakestore.UI.Adapter.ViewPager.ViewPagerAdapter
import com.example.fakestore.UI.Electronics.ElectronicsFragment
import com.example.fakestore.UI.Jewelery.JeweleryFragment
import com.example.fakestore.UI.Mens.MensFragment
import com.example.fakestore.UI.Product.ProductActivity
import com.example.fakestore.UI.Womens.WomensFragment
import com.example.fakestore.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = ViewPagerAdapter(supportFragmentManager)

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager()
        tabLayout()
    }

    private fun viewPager(){
        adapter.addFragment(WomensFragment(), "Womens")
        adapter.addFragment(ElectronicsFragment(), "Electronics")
        adapter.addFragment(JeweleryFragment(), "")
        adapter.addFragment(MensFragment(), "Mens")
        binding.viewPager.adapter = adapter
    }

    private fun tabLayout(){
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.getTabAt(0)!!.setText("Womens")
        binding.tabLayout.getTabAt(1)!!.setText("Electronics")
        binding.tabLayout.getTabAt(2)!!.setText("Jewelery")
        binding.tabLayout.getTabAt(3)!!.setText("Mens")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.productMenu->{
                val intent = Intent(this@MainActivity, ProductActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}