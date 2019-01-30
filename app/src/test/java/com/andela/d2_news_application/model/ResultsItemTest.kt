package com.andela.d2_news_application.model

import org.junit.Assert.*
import org.junit.Test

class ResultsItemTest {

    @Test
    fun testDefaultValues() {
        val result = ResultsItem(section = "Politics", title = "Trump never")
        assertEquals("Politics", result.section)
        assertEquals("Trump never", result.title)
    }
}