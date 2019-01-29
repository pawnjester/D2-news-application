package com.andela.d2_news_application.model

import org.junit.Assert.*
import org.junit.Test

class FoodResultsTest {

    @Test
    fun testDefaultValues() {
        val result = FoodResults(section = "dessert", title = "lasagna")
        assertEquals("dessert", result.section)
        assertEquals("lasagna", result.title)
    }
}