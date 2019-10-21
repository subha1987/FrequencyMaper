package org.subha.frequencymaper.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build


fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        ?: return false

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val networks = connectivityManager.allNetworks ?: return false
        var networkInfo: NetworkInfo
        for (mNetwork in networks) {
            networkInfo = connectivityManager.getNetworkInfo(mNetwork)
            if (networkInfo.isConnected) {
                return true
            }
        }
    } else {
        val info = connectivityManager.allNetworkInfo ?: return false
        for (i in info.indices) {
            if (info[i].isConnected) {
                return true
            }
        }
    }

    return false
}