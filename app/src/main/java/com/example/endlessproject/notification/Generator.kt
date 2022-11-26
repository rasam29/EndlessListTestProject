package com.example.endlessproject.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AlignmentSpan
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.endlessproject.R
import kotlin.random.Random

var defaultChannelId:String = ""

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
            NotificationCompat.Action()
            for (channelData in groupData.t) {
                defaultChannelId = if (channelData.isDefault) channelData.id?:"" else ""
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
            if (defaultChannelId.isEmpty()) throw IllegalArgumentException("you have to set one of the channels as default!")
        }
    }
}

fun Context.registerGroupLessChannels(channels: MutableList<Channel>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        for (channelData in channels) {
            defaultChannelId = if (channelData.isDefault) channelData.id?:"" else ""
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
        if (defaultChannelId.isEmpty()) throw IllegalArgumentException("you have to set one of the channels as default!")
    }
}


fun Context.showNotification(config: NotificationConfig) {
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (config.notifyId == null) {
        config.notifyId = Random.nextInt()
    }
    config.channelId = notificationManager.normalizeChannelId(config.channelId)
    NotificationManagerCompat.from(this).areNotificationsEnabled()
    val builder = with(NotificationCompat.Builder(this, config.channelId!!)) {
        setContentTitle(config.titleText?.getTextWithRightAlignment())
        setContentText(config.contentText.getTextWithRightAlignment())
        priority = Importance.Urgent.value
        setSubText(config.contentInfo)
        //same for sub text but below android N it uses this content info.subText is precedence
        setContentInfo(config.contentInfo)
        setVisibility(Visibility.VISIBILITY_PUBLIC.value)
        setAutoCancel(config.autoCancel)
        setSmallIcon(config.smallIcon)
        color = 0X000000
        var soundAndVibrationBehaviour :Int? = null
        if (config.behaviour.enableVibration) {
            soundAndVibrationBehaviour = Notification.DEFAULT_SOUND
        }
        if (config.behaviour.enableSound){
            soundAndVibrationBehaviour = Notification.DEFAULT_VIBRATE
        }
        soundAndVibrationBehaviour?.let { setDefaults(it) }

        setColorized(true)
    }


    with(NotificationManagerCompat.from(this)) {
        notify(config.notifyId!!, builder.build())
    }
}


fun NotificationManager.normalizeChannelId(channelId: String?): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        return if (getNotificationChannel(channelId) == null || channelId.isNullOrEmpty()) {
            defaultChannelId
        } else channelId
    } else {
        defaultChannelId
    }
}

fun String.getTextWithRightAlignment(): CharSequence {
    val t: Spannable = SpannableString(this)
    t.setSpan(
        AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL),
        0,
        t.length,
        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
    )
    return t
}

private const val CHANNEL_ID_SOCIAL = "social"
const val CHANNEL_ID_DOWNLOAD = "download"
private const val CHANNEL_ID_UPDATE = "update"
private const val CHANNEL_ID_REVIEW = "review"
private const val CHANNEL_ID_PROMOTION = "promotion"

