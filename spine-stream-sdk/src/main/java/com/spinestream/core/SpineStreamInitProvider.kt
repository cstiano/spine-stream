package com.spinestream.core

import android.util.Log

class SpineStreamInitProvider : InitProvider() {

    override fun onCreate(): Boolean {
        setUpSpineStream()
        return true
    }

    private fun setUpSpineStream() {
        Log.i("SpineStreamSDK", "Initialize SpineStreamSDK")
    }
}