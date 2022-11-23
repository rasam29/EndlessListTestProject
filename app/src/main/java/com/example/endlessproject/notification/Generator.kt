package com.example.endlessproject.notification

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.endlessproject.R
import kotlin.random.Random
import kotlin.random.nextInt

fun Context.registerChannelsAndGroups(groups: MutableList<GroupCategory>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        for (groupData in groups) {
            val tempGroup = NotificationChannelGroup(groupData.groupId, groupData.groupName)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                tempGroup.description = groupData.groupDescription
            }
            notificationManager.createNotificationChannelGroup(tempGroup)

            for (channelData in groupData.t) {
                val mChannel = NotificationChannel(
                    channelData.id,
                    channelData.title,
                    channelData.importance.value
                ).apply {
                    description = channelData.description
                    enableLights(channelData.defaultBehavior.enableLights)
                    setShowBadge(channelData.enableBadge)
                    lockscreenVisibility =
                        channelData.defaultBehavior.privacySetting.visibility.value
                    enableVibration(channelData.defaultBehavior.enableVibration)
                    group = groupData.groupId
                }
                notificationManager.createNotificationChannel(mChannel)
            }
        }
    }
}

fun Context.registerGroupLessChannels(channels: MutableList<Channel>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        for (channelData in channels) {
            val mChannel = NotificationChannel(
                channelData.id,
                channelData.title,
                channelData.importance.value
            ).apply {
                description = channelData.description
                setShowBadge(channelData.enableBadge)
                lockscreenVisibility = channelData.defaultBehavior.privacySetting.visibility.value
                enableLights(channelData.defaultBehavior.enableLights)
                enableVibration(channelData.defaultBehavior.enableVibration)
            }
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}


//fun syncChannelAndGroups(){}


fun Context.showNotification() {
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if ("notifId".isNullOrEmpty()){
        //set random notif id
        Random.nextInt()
    }
    if (!notificationManager.isChannelExist("123") || "{channelID}".isNullOrEmpty()){
        //switch to default channel

    }

    var builder = NotificationCompat.Builder(this, "channelone")
        .setContentTitle("this is content title")
        .setContentText("this is content text")
        .setPriority(Importance.Urgent.value)
        .setSubText("this is sub text")
        //same for sub text but below android N it uses this content info. subText is precedence
        .setContentInfo("this is content info")
        .setVisibility(Visibility.VISIBILITY_PUBLIC.value)
        .setAutoCancel(false)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setColor(0X000000)
        .setVibrate(longArrayOf(0L,0L,0L))
        .setDefaults(0)
        .setExpirationDate
        .setColorized(true)

    with(NotificationManagerCompat.from(this)) {
        // notificationId is a unique int for each notification that you must define
        notify(23, builder.build())
    }
}


fun NotificationManager.isChannelExist(channelId: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        getNotificationChannel(channelId) != null
    } else {
        true
    }
}

