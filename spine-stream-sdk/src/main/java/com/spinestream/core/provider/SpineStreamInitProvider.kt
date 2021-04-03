package com.spinestream.core.provider

import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import com.spinestream.core.channels.ChannelsManager
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SpineStreamInitProvider : InitProvider() {

    private val channelsManager = ChannelsManager()

    override fun onCreate(): Boolean {
        setupSpineStreamSDK()
        return true
    }

    private fun setupSpineStreamSDK() {
        Log.i("SpineStreamSDK", "Initialize Spine Stream SDK")
        ProcessLifecycleOwner.get().lifecycle.addObserver(channelsManager)
    }
}