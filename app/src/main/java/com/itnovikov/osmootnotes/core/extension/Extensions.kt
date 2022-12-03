package com.itnovikov.osmootnotes.core.extension

import android.util.Log

fun log(tag: String = "TAG", message: String) {
    Log.d(tag, "log: $message")
}