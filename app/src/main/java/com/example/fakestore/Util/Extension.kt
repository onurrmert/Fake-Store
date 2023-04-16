package com.example.fakestore.Util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NotificationCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.fakestore.R
import com.example.fakestore.UI.Product.ProductActivity

class Extension {
    companion object{

        fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        fun FragmentActivity.backpress(backTime1 : Long, viewLifecycleOwner : LifecycleOwner){
            var backTime = backTime1
            this@backpress.onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
                    override fun handleOnBackPressed() {
                        if (backTime + 3000 > System.currentTimeMillis()){
                            this@backpress.finish()
                        }else{
                            Toast.makeText(this@backpress.applicationContext, "Click again to exit!", Toast.LENGTH_SHORT).show()
                        }
                        backTime = System.currentTimeMillis()
                    }
                })
        }

        @SuppressLint("UnspecifiedImmutableFlag")
        private fun notificationBuilder(context: Context, resources: Resources) : NotificationCompat.Builder{

            val fullScreenIntent = Intent(context, ProductActivity::class.java)

            val fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)

           return  NotificationCompat.Builder(context, "channelId")
                .setSmallIcon(R.drawable.baseline_shopping_cart_24)
                .setContentTitle("Fake Store")
                .setContentText("Product added to cart")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.baseline_shopping_cart_24))
                .setContentIntent(fullScreenPendingIntent)
        }


        fun Context.sendNotification(resources: Resources){
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel("channelId", "description", NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.enableVibration(false)

                notificationManager.createNotificationChannel(notificationChannel)

                notificationBuilder(this, resources)
            } else {
                notificationBuilder(this, resources)
            }
            notificationManager.notify(1234, notificationBuilder(this, resources).build())
        }
    }
}