package com.challenge.core.util

import java.lang.Exception

fun getPageIntFromUrl(url: String): Int? {
    return try {
        url.substringAfterLast("=").toInt()
    } catch (e: Exception) {
        null
    }
}
