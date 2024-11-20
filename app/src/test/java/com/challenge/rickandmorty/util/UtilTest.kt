package com.challenge.rickandmorty.util

import com.challenge.core.util.getPageIntFromUrl
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilTest {
    @Test
    fun `Given a URL with integer at the end, When getPageIntFromUrl is called, Then it should return the Integer`() {
        val url = "http://example.com/page?id=42"
        val result = getPageIntFromUrl(url)
        assertEquals(42, result)
    }

    @Test
    fun `Given a URL without integer at the end, When getPageIntFromUrl is called, Then it should return null`() {
        val url = "http://example.com/page?id="
        val result = getPageIntFromUrl(url)
        assertEquals(null, result)
    }

    @Test
    fun `Given a URL without = at the end, When getPageIntFromUrl is called, Then it should return null`() {
        val url = "http://example.com/page?id"
        val result = getPageIntFromUrl(url)
        assertEquals(null, result)
    }

    @Test
    fun `Given a URL with a non-integer value at the end, When getPageIntFromUrl is called, Then it should return null`() {
        val url = "http://example.com/page?id=abc"
        val result = getPageIntFromUrl(url)
        assertEquals(null, result)
    }

    @Test
    fun `Given an empty URL, When getPageIntFromUrl is called, Then it should return null`() {
        val url = ""
        val result = getPageIntFromUrl(url)
        assertEquals(null, result)
    }
}
