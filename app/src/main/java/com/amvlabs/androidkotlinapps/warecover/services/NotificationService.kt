package com.amvlabs.androidkotlinapps.warecover.services

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import com.amvlabs.androidkotlinapps.warecover.utils.Constants.WHATSAPP
import kotlin.math.log

private const val TAG = "car"
class NotificationService: NotificationListenerService() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if(sbn?.notification?.flags == Notification.FLAG_GROUP_SUMMARY){
            return
        }

        if(sbn != null){
            when(sbn.packageName){
                WHATSAPP -> {checkWhatsappNotification(sbn)}
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.d(TAG, "onNotificationRemoved: ")
    }

    companion object{
        private lateinit var onNotificationReceive: OnNotificationReceive
        fun setOnNotificaitonListener(onNotificationReceive: OnNotificationReceive){
            this.onNotificationReceive =onNotificationReceive
        }
        private fun checkWhatsappNotification(sbn: StatusBarNotification) {
            val bundle = sbn.notification.extras
            val from = bundle.getString(Notification.EXTRA_TITLE)
            val message =  bundle.getString(Notification.EXTRA_TEXT)
            onNotificationReceive.onReceive(from.toString(),message.toString())
//            Log.d(TAG, "onNotificationPostd: $from  $message")
        }
    }
}

interface OnNotificationReceive{
    fun onReceive(userName:String,message:String)
}
