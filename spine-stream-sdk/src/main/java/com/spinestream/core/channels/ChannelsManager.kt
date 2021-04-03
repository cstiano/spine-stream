package com.spinestream.core.channels

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ChannelsManager : LifecycleObserver {

    private val channels = SharedChannels.provideChannels()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("SpineStreamSDK", "onCreate channels manager")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d("SpineStreamSDK", "onStart channels manager")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d("SpineStreamSDK", "onStop channels manager")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.d("SpineStreamSDK", "onDestroy Channel Spine Stream SDK")
        for (channel in channels) {
            channel.value.close()
        }
    }
}