package com.example.fakestore.Util

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.fakestore.Data.remote.Model.StoreModel
import com.example.fakestore.Data.remote.Model.StoreModelItem
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

        fun Context.connectionControl() : Boolean{
            val manager =
                this.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.activeNetworkInfo

            if (networkInfo == null) {
                return false// is not connect internet
            }else{
                return true// connect internet
            }
        }

        private fun notificationBuilder(context: Context) : NotificationCompat.Builder{

            val fullScreenIntent = Intent(context, ProductActivity::class.java)

            val fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)

           return  NotificationCompat.Builder(context, "channelId")
                .setSmallIcon(R.drawable.baseline_shopping_cart_24)
                .setContentTitle("Fake Store")
                .setContentText("Product added to cart")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(fullScreenPendingIntent)
        }

        fun Context.sendNotification(){
            with(NotificationManagerCompat.from(this)) {
                if (ActivityCompat.checkSelfPermission(
                        this@sendNotification,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@with
                }
                notify(1234, notificationBuilder(this@sendNotification).build())
            }
        }

        fun Context.sortDesc(storeModel : StoreModel) : List<StoreModelItem>{
            return storeModel.sortedByDescending { it.price }
        }

        fun Context.sortAsc(storeModel : StoreModel) : List<StoreModelItem>{
            return storeModel.sortedBy { it.price }
        }
    }
}