package com.example.endlessproject.notification


data class TempGroupCategory(val x: MutableList<GroupCategory> = mutableListOf())

data class TempChannel(val y: MutableList<Channel> = mutableListOf())

@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class Dsl

@Suppress("FunctionName")
fun GroupChannelBuilder(parent: (@Dsl TempGroupCategory).() -> Unit): MutableList<GroupCategory> {
    return TempGroupCategory().apply(parent).x
}

@Suppress("FunctionName")
fun ChannelBuilder(channelBuilder: (@Dsl TempChannel).() -> Unit): MutableList<Channel> {
    return TempChannel().apply(channelBuilder).y
}

@Suppress("FunctionName", "SpellCheckingInspection")
fun TempGroupCategory.Groupz(groupBuilder: (@Dsl GroupCategory).() -> Unit) {
    val tempGroup = GroupCategory().apply(groupBuilder)
    x.add(tempGroup)
}


@Suppress("FunctionName", "SpellCheckingInspection")
fun GroupCategory.Channelz(channelBuilder: (@Dsl Channel).() -> Unit) {
    val tempChannel = Channel().apply(channelBuilder)
    t.add(tempChannel)
}

@Suppress("FunctionName", "SpellCheckingInspection")
fun TempChannel.Channelz(channelBuilder: (@Dsl Channel).() -> Unit) {
    val temp = Channel().apply(channelBuilder)
    y.add(temp)
}

@Suppress("FunctionName", "SpellCheckingInspection")
fun Channel.Behaviourz(behaveBuilder: (@Dsl Behaviour).() -> Unit) {
    val tempBehaviour = Behaviour().apply(behaveBuilder)
    defaultBehavior = tempBehaviour
}


@Suppress("FunctionName", "SpellCheckingInspection")
fun Notifz(notificationBuilder:(@Dsl NotificationConfig).()->Unit):NotificationConfig{
    return NotificationConfig().apply(notificationBuilder)
}

@Suppress("FunctionName", "SpellCheckingInspection")
fun NotificationConfig.Privazy(privacySettingBuilder:(@Dsl PrivacySetting).()->Unit){
    privacySetting.apply(privacySettingBuilder)
}

@Suppress("FunctionName", "SpellCheckingInspection")
fun NotificationConfig.Behaviourz(behavoiurBuilder:(@Dsl Behaviour).()->Unit){
    behaviour.apply(behavoiurBuilder)
}










val notif = Notifz {
    titleText = ""

}














val dslTest = GroupChannelBuilder {
    Groupz {
        groupId = "123"
        groupName = "first group"
        groupDescription = "only showed on android P, first group desc"
        Channelz {
            id = "channelone"
            title = "channel one"
            description = "this is test for one"
            enableBadge = true
            Behaviourz {
                privacySetting =
                    PrivacySetting().apply { visibility = Visibility.VISIBILITY_PUBLIC }
                enableLights = true
                enableVibration = true
            }
        }
        Channelz {
            id = "channeltwo"
            title = "channel two"
            description = "this is test for two"
            enableBadge = false
            Behaviourz {
                privacySetting =
                    PrivacySetting().apply { visibility = Visibility.VISIBILITY_SECRET }
                enableLights = false
                enableVibration = false
            }
        }

    }


}
