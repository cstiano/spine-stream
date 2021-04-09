package com.spinestream.core.provider

import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import com.spinestream.core.BuildConfig
import com.spinestream.core.channels.ChannelsManager
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SpineStreamInitProvider : InitProvider() {

    private val channelsManager = ChannelsManager()

    override fun onCreate(): Boolean {
        initSpineStreamSDK()
        return true
    }

    private fun initSpineStreamSDK() {
        if (BuildConfig.DEBUG) {
            Log.d("SpineStreamSDK", "Initializing Spine Stream SDK")
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(channelsManager)
    }
}