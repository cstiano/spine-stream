package com.spinestream.core

import com.spinestream.core.handler.Handler
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
object SpineStream : Handler() {

    interface Publisher

    interface Subscriber
}