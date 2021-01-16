package com.warmpot.android.formvalidationtdd.validator

import org.junit.Assert.assertEquals
import org.junit.Test

class TextFieldTest {

    private val getTextCallback: () -> String = { "Awesome" }

    @Test
    fun `getText should be invoked`() {
        val textField = TextField(getText = getTextCallback)
        val result = textField.getText()
        assertEquals(result, "Awesome")
    }
}