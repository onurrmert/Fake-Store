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

        notificationBuilder()

        binding.floatingActionButton.setOnClickListener {
            AlertDialog.Builder(this@DetailActivity).apply {
                this.setMessage("Do you want to add product to cart?")
                this.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    sendNotification()
                })
            }.show()
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun notificationBuilder(){

        val fullScreenIntent = Intent(this, MainActivity::class.java)

        fullScreenIntent.putExtra("id", getID())

        val fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
            fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.baseline_shopping_cart_24)
            .setContentTitle("Fake Store")
            .setContentText("Product added to cart")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(fullScreenPendingIntent)
    }

    private fun sendNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.descriptionText)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@DetailActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@with
            }
            notify(1234, builder.build())
        }
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


