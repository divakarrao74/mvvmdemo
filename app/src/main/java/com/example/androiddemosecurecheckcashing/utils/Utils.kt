package com.example.androiddemosecurecheckcashing.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.androiddemosecurecheckcashing.application.DemoApplication


object Utils {

      fun haveNetworkConnection(context: Context?): Boolean {
          val connectivityManager =
              context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
          val networkInfo = connectivityManager.activeNetworkInfo
          return networkInfo != null && networkInfo.isConnected
    }
}