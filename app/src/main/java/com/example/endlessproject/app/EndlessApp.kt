package com.example.endlessproject.app

import android.app.Application
import android.content.Context
import android.net.*
import android.net.NetworkCapabilities.*
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSpecifier
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.endlessproject.notification.dslTest
import com.example.endlessproject.notification.registerChannelsAndGroups
import dagger.hilt.android.HiltAndroidApp
import java.lang.reflect.Field


@HiltAndroidApp
class EndlessApp : Application() {
    override fun onCreate() {
        super.onCreate()
        registerChannelsAndGroups(
            dslTest
        )



    }


//    @RequiresApi(Build.VERSION_CODES.R)
//    fun Context.switchNetwork() {
//        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val network = connectivityManager.activeNetwork
//            val capabilities = connectivityManager.getNetworkCapabilities(network)
//            if (capabilities?.isWifiCapable() == true){
//                val networkCallback = object : ConnectivityManager.NetworkCallback() {
//                    override fun onAvailable(network: Network) {
//                        super.onAvailable(network)
//                        Log.d("NETTAQ","onAvailable")
//                    }
//
//                    override fun onLost(network: Network) {
//                        super.onLost(network)
//                        Log.d("NETTAQ","network lost")
//
//                    }
//                }
//                val wifiNetworkSpecifier = TelephonyNetworkSpecifier.Builder()
//                    .setSubscriptionId(12)
//                    .build()
//
//                val networkRequest = NetworkRequest.Builder()
//                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//// Add the below 2 lines if the network should have internet capabilities.
//// Adding/removing other capabilities has made no known difference so far
//    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//    .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
//                    .setNetworkSpecifier(wifiNetworkSpecifier)
//                    .build()
//                connectivityManager.requestNetwork(networkRequest, networkCallback)
//            }
//            Log.d("NETTAQ","wifi is ${capabilities?.isWifiCapable()}")
//            Log.d("NETTAQ","data is ${capabilities?.isCellularCapable()}")
//
//        }
//    }

    fun NetworkCapabilities.isWifiCapable():Boolean = hasTransport(NetworkCapabilities.TRANSPORT_WIFI)

    fun NetworkCapabilities.isCellularCapable():Boolean = hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    fun Context.isWifiEnable():Boolean{
        val wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

}
