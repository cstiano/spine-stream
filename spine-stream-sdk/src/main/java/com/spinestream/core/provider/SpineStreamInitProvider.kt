package com.spinestream.core.provider

import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import com.spinestream.core.channels.ChannelsManager
import kotlinx.coroutines.ExperimentalCoroutinesApi

class SpineStreamInitProvider : InitProvider() {

    @ExperimentalCoroutinesApi
    private val channelsManager = ChannelsManager()

    override fun onCreate(): Boolean {
        setupSpineStreamSDK()
        return true
    }

    private fun setupSpineStreamSDK() {
        Log.i("SpineStreamSDK", "Initialize Spine Stream SDK")
        ProcessLifecycleOwner.get().getLifecycle().addObserver(channelsManager)
    }
}