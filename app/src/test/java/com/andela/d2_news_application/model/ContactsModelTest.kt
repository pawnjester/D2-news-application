package com.andela.d2_news_application.model

import org.junit.Assert.*
import org.junit.Test

class ContactsModelTest{

    @Test
    fun testDefaultValues() {
        val result = ContactsModel(contactNumber = "08134509008", contactName = "shirley")
        assertEquals("08134509008", result.contactNumber)
        assertEquals("shirley", result.contactName)
    }
}