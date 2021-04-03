package com.spinestream.sample.user

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.spinestream.core.SpineStream

@Suppress("EXPERIMENTAL_API_USAGE")
class UserService : Service() {

    private val users = arrayOf(UserInfo("Maria", "maria@email.com"), UserInfo("Joao", "joao@email.com"))

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        users.forEach { userInfo ->
            SpineStream.publish(userInfo)
        }
    }
}