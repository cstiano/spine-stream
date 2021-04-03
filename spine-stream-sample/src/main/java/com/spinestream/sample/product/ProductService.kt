package com.spinestream.sample.product

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.spinestream.core.SpineStream

@Suppress("EXPERIMENTAL_API_USAGE")
class ProductService : Service() {
    private val products = arrayOf(ProductInfo(1, "Product 1"), ProductInfo(2, "Product 2"))

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        products.forEach { productInfo ->
            SpineStream.publish(productInfo)
        }
    }

}