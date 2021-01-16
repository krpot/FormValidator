package com.warmpot.android.formvalidationtdd.validator

import junit.framework.Assert.assertTrue
import org.junit.Test

class RadioGroupFieldTest {

    @Test
    fun `isChecked should be invoked`() {
        val formField = RadioGroupField { true }
        assertTrue(formField.isChecked())
    }
}