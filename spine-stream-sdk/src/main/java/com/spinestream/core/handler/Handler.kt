package com.spinestream.core.handler

import androidx.annotation.VisibleForTesting
import com.spinestream.core.channels.SharedChannels
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

@ExperimentalCoroutinesApi
open class Handler : Publisher, Subscriber {

    @VisibleForTesting
    val channels by lazy { SharedChannels.provideChannels() }

    override fun publish(event: Any) {
        val className = event::class.toString()
        createChannelIfNecessary(className)
        GlobalScope.launch {
            channels[className]?.send(event)
        }
    }

    override fun <T : Any> subscribe(eventClass: KClass<T>, onEvent: suspend (T) -> Unit) {
        val className = eventClass.toString()
        createChannelIfNecessary(className)
        GlobalScope.launch {
            channels[className]?.openSubscription().let { channel ->
                if (channel != null) {
                    for (event in channel) {
                        onEvent(event as T)
                    }
                }
            }
        }
    }

    private fun createChannelIfNecessary(eventClassName: String) {
        if (channels[eventClassName] == null) {
            channels[eventClassName] = BroadcastChannel(64)
        }
    }
}