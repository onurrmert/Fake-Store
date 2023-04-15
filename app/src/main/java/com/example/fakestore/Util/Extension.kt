package com.example.fakestore.Util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner

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
    }
}