package com.spinestream.core.channels

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.spinestream.core.BuildConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import java.util.concurrent.ConcurrentHashMap

@ExperimentalCoroutinesApi
class ChannelsManager : LifecycleObserver {

    init {
        SharedChannels.createChannels(ConcurrentHashMap<String, BroadcastChannel<Any>>(DEFAULT_CAPACITY))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (BuildConfig.DEBUG) {
            Log.d("SpineStreamSDK", "onDestroy Channel Spine Stream SDK")
        }

        val channels = SharedChannels.provideChannels()
        for (channel in channels) {
            channel.value.close()
        }
    }
}