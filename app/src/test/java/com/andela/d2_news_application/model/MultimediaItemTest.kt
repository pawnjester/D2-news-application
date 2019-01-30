package com.andela.d2_news_application.model

import org.junit.Assert.*
import org.junit.Test

class MultimediaItemTest {

    @Test
    fun testDefaultValues() {
        val result = MultimediaItem(url = "exmaple@example.com", type = "jpg")
        assertEquals("exmaple@example.com", result.url)
        assertEquals("jpg", result.type)
    }
}