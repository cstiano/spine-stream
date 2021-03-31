package com.spinestream.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.spinestream.core.ToasterMessage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ToasterMessage.s(this, "test message")
    }
}