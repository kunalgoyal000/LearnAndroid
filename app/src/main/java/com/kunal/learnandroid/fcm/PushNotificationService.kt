package com.kunal.learnandroid.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        //update our server
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        //respond to received messages
    }
}