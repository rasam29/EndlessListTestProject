package com.example.endlessproject.app

import android.app.Application
import com.uxcam.UXCam
import com.uxcam.datamodel.UXConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EndlessApp : Application(){
    override fun onCreate() {
        super.onCreate()
        val config: UXConfig = UXConfig.Builder("e3o3cdavzdlfrfp")
            .enableAutomaticScreenNameTagging(true)
            .enableImprovedScreenCapture(true)
            .enableCrashHandling(true)
            .enableMultiSessionRecord(true)
            .build()
        UXCam.startWithConfiguration(config)
    }
}