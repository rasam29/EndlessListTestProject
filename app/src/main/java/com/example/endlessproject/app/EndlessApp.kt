package com.example.endlessproject.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
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
        switchNetwork()


    }


    fun Context.switchNetwork() {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)

            val clazz: Class<*> = capabilities!!.javaClass::class.java
            for (field in clazz.javaClass.declaredFields){
                Log.d("DECLARED FIELDZ",field.name)
            }
            for (field in clazz.javaClass.fields){
                Log.d("FIELDZ",field.name)
            }
            val mTransportType: Field = clazz.javaClass.superclass.getField("mTransportTypes")
            mTransportType.isAccessible = true
            mTransportType.setLong(clazz, TRANSPORT_CELLULAR.toLong() )
            Log.d("NETTAQ","wifi is ${capabilities?.isWifiCapable()}")
            Log.d("NETTAQ","data is ${capabilities?.isCellularCapable()}")
            if(capabilities!= null && capabilities.isWifiCapable() and capabilities.isCellularCapable()){

            }
        }
    }

    fun NetworkCapabilities.isWifiCapable():Boolean = hasTransport(NetworkCapabilities.TRANSPORT_WIFI)

    fun NetworkCapabilities.isCellularCapable():Boolean = hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    fun Context.isWifiEnable():Boolean{
        val wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

//    fun Context.isCellularEnable():Boolean{
//
//    }

}
