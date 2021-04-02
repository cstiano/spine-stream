package com.spinestream.core

import android.content.Context
import android.widget.Toast

class ToasterMessage {
    companion object {
        fun s(c: Context, message: String) {
            Toast.makeText(c, message, Toast.LENGTH_SHORT).show()
        }
    }
}