

package com.example.endlessproject.notification

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.endlessproject.R

data class NotificationConfig(
    var notifyId: Int?= null,
    var channelId: String?= null,
    var behaviour: Behaviour = Behaviour(),
    var titleText: String?=null,
    var contentText: String = "",
    /**
        replaced with subtext in some version but some versions are still using
        contentInfo.
     */
    var contentInfo:String = "",
    var impressionCallBackUrl:String?= null,
    var smallIcon: Int = R.drawable.ic_launcher_foreground,
    var autoCancel: Boolean = true,
    var impressionSetting:ImpressionSetting = ImpressionSetting()
)
data class Actions(
    var action:String,
    var extras:
)
data class ImpressionSetting(
    var maxTryCount:Int = 10,
    var openCallbackUrl:String,
    var dismissCallbackUrl:String,
    var impressionCallbackUrl:String
)

enum class Visibility(val value: Int) {
    @Suppress("unused")
    VISIBILITY_PRIVATE(0),
    VISIBILITY_PUBLIC(1),
    VISIBILITY_SECRET(-1)

}

data class PrivacySetting(
    var visibility: Visibility = Visibility.VISIBILITY_PUBLIC,
    /**
        works on android 12 and higher (coming soon)
        authentication required after you click on notification
     */
    var authenticationRequired: Boolean = false,
)

data class GroupCategory(
    var groupId: String?= null,
    var groupName: String?= null,
    /**
        only showed in Android P and higher
     */
    var groupDescription: String?=null,
    val t: MutableList<Channel> = mutableListOf()
)
enum class Importance(val value: Int){
    /**
        makes sound and showed in heads up notification
     */
    Urgent(NotificationManagerCompat.IMPORTANCE_HIGH),
    /**
        high, makes sound
     */
    Default(NotificationManagerCompat.IMPORTANCE_DEFAULT),

    /**
        no sound
     */
    @Suppress("unused")
    Medium(NotificationManagerCompat.IMPORTANCE_LOW),
    /**
        no sound and nothing shows in status bar
     */
    @Suppress("unused")
    Low(NotificationManagerCompat.IMPORTANCE_MIN),
}
data class Channel(
    var id: String?= null,
    var importance: Importance = Importance.Default,
    var title: String?= null,
    var description: String?= null,
    var enableBadge:Boolean = true,
    var defaultBehavior: Behaviour = Behaviour(),
    var isDefault:Boolean = true
)


data class Behaviour(
    var priority: Int = NotificationCompat.PRIORITY_DEFAULT,
    var enableLights: Boolean = true,
    var enableVibration:Boolean = true,
    var enableSound:Boolean = true,
    var privacySetting: PrivacySetting = PrivacySetting()
)



