package com.spinestream.core.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import java.util.concurrent.ConcurrentHashMap

@ExperimentalCoroutinesApi
object SharedChannels {

    private var channels: ConcurrentHashMap<String, BroadcastChannel<Any>>? = null

    fun createChannels(channels: ConcurrentHashMap<String, BroadcastChannel<Any>>) {
        this.channels = channels
    }

    fun provideChannels() = channels ?: ConcurrentHashMap<String, BroadcastChannel<Any>>(DEFAULT_CAPACITY)
}