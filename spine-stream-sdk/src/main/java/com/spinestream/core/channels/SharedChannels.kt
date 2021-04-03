package com.spinestream.core.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import java.util.concurrent.ConcurrentHashMap

@ExperimentalCoroutinesApi
object SharedChannels {

    private val channels = ConcurrentHashMap<String, BroadcastChannel<Any>>(64)

    fun provideChannels() = channels
}