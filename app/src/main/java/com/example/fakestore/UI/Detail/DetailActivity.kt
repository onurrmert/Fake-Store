package com.example.fakestore.UI.Detail

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fakestore.Data.remote.Model.StoreModelItem
import com.example.fakestore.MainActivity
import com.example.fakestore.R
import com.example.fakestore.UI.Mens.MensFragment
import com.example.fakestore.UI.Product.ProductActivity
import com.example.fakestore.Util.Extension.Companion.sendNotification
import com.example.fakestore.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(DetailViewModel::class.java)
    }

    private val channelId = "StoreNotifications"

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private lateinit var builder : NotificationCompat.Builder

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getOneData(getID())
        getData()

        binding.floatingActionButton.setOnClickListener {
            AlertDialog.Builder(this@DetailActivity).apply {
                this.setMessage("Do you want to add product to cart?")
                this.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    viewModel.insert(getID())
                    this@DetailActivity.sendNotification()
                })
            }.show()
        }
    }

    private fun getID() : Int{
        return intent.getIntExtra("id", 0)
    }

    private fun getData(){
        viewModel.storeModelItem.observe(this, {
                item->
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


