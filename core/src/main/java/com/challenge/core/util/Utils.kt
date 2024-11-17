package com.challenge.core.util

import android.util.Log
import java.lang.Exception

fun getPageIntFromUrl(url: String): Int? {
    return try {
        url.substringAfterLast("=").toInt()
    } catch (e: Exception) {
        e.message?.let { Log.d("converting url", it) }
        null
    }
}