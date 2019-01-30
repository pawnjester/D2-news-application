package com.andela.d2_news_application.model

import org.junit.Assert.*
import org.junit.Test

class FashionResultsTest {

    @Test
    fun testDefaultValues() {
        val result = FashionResults(section = "blouse", title = "cropTop")
        assertEquals("blouse", result.section)
        assertEquals("cropTop", result.title)
    }
}