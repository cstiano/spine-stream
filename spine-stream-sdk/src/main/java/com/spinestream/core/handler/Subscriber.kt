package com.spinestream.core.handler

import kotlin.reflect.KClass

interface Subscriber {

    fun <T : Any> subscribe(eventClass: KClass<T>, onEvent: suspend (T) -> Unit)
}