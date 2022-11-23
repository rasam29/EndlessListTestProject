package com.example.endlessproject.app

import android.app.Application
import android.app.NotificationManager
import com.example.endlessproject.notification.*
import dagger.hilt.android.HiltAndroidApp
import java.util.Random

@HiltAndroidApp
class EndlessApp : Application(){
    override fun onCreate() {
        super.onCreate()
        registerChannelsAndGroups(
            dslTest
        )
    }
}
