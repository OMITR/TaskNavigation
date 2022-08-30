package com.jonechka.tasknavigation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.jonechka.tasknavigation.util.NotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskApp: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationService.CHANNEL_ID,
                "Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Used for sending notifications to user"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}