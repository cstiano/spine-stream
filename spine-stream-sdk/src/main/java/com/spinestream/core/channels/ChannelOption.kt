package com.spinestream.core.channels

import androidx.annotation.IntDef

const val DEFAULT_CAPACITY: Int = 64

@IntDef(DEFAULT_CAPACITY)
@Retention(AnnotationRetention.SOURCE)
annotation class ChannelOption