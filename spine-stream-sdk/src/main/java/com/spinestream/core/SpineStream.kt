package com.spinestream.core

import com.spinestream.core.handler.Handler
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
object SpineStream : Handler() {}

@ExperimentalCoroutinesApi
inline fun <reified T : Any> SpineStream.subscribe(noinline onEvent: suspend (T) -> Unit) =
    SpineStream.subscribe(T::class, onEvent)