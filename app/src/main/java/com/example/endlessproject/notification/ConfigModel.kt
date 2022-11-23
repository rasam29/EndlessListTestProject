package com.example.endlessproject.notification

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

data class NotificationConfig(
    var notifyId: String?= null,
    var channelId: String?= null,
    var behaviour: Behaviour = Behaviour(),
    var titleText: String?=null,
    var contentText: String = "",
    //other calles it sublime text
    var contentInfo:String = "",
    var smallIcon: String?= null,
    var autoCancel: Boolean = true,
)

enum class Visibility(val value: Int) {
    VISIBILITY_PRIVATE(0),
    VISIBILITY_PUBLIC(1),
    VISIBILITY_SECRET(-1)

}

data class PrivacySetting(
    var visibility: Visibility = Visibility.VISIBILITY_PUBLIC,
    //works on android 12 and higher (coming soon)
    var authenticationRequired: Boolean = false,
)

data class GroupCategory(
    var groupId: String?= null,
    var groupName: String?= null,
    //requires Android P
    var groupDescription: String?=null,
    val t: MutableList<Channel> = mutableListOf()
)
enum class Importance(val value: Int){
    Urgent(NotificationManagerCompat.IMPORTANCE_HIGH),//makes sound and showed in heads up notification
    Default(NotificationManagerCompat.IMPORTANCE_DEFAULT),//high, makes sound
    Medium(NotificationManagerCompat.IMPORTANCE_LOW),//no sound
    Low(NotificationManagerCompat.IMPORTANCE_MIN),//no sound and nothing shows in status bar
}
data class Channel(
    var id: String?= null,
    var importance: Importance = Importance.Default,
    var title: String?= null,
    var description: String?= null,
    var enableBadge:Boolean = true,
    var defaultBehavior: Behaviour = Behaviour()
)


data class Behaviour(
    var priority: Int = NotificationCompat.PRIORITY_DEFAULT,
    var enableLights: Boolean = true,
    var enableVibration:Boolean = false,
    var privacySetting: PrivacySetting = PrivacySetting()

)



