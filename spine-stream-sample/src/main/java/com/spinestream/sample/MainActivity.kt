package com.spinestream.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.spinestream.core.SpineStream
import com.spinestream.core.subscribe
import com.spinestream.sample.product.ProductInfo
import com.spinestream.sample.product.ProductService
import com.spinestream.sample.user.UserInfo
import com.spinestream.sample.user.UserService

@Suppress("EXPERIMENTAL_API_USAGE")
class MainActivity : AppCompatActivity() {
    private val TAG = "SpineStreamSample"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SpineStream.subscribe<UserInfo> { event ->
            Log.d(TAG, "Received user: $event")
        }

        SpineStream.subscribe<ProductInfo> { event ->
            Log.d(TAG, "Received product: $event")
        }

        Intent(this, UserService::class.java).also { intent ->
            startService(intent)
        }

        Intent(this, ProductService::class.java).also { intent ->
            startService(intent)
        }
    }
}