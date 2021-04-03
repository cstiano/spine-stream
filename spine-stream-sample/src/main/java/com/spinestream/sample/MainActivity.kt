package com.spinestream.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spinestream.core.SpineStream
import com.spinestream.core.subscribe

@Suppress("EXPERIMENTAL_API_USAGE")
class MainActivity : AppCompatActivity() {

    data class UserInfo(val name: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = UserInfo("Cris")
        SpineStream.publish(user)
        SpineStream.subscribe<UserInfo> { event ->
            println(event)
        }
    }
}