package com.warmpot.android.formvalidationtdd.validator

import org.junit.Assert.*
import org.junit.Test

class FormValidatorTest {

    @Test
    fun `addField should add text field`() {
        val formValidator = FormValidator()
        formValidator.addField(TextField(getText = {""}))
        assertTrue(formValidator.formFields.first() is TextField)
    }

    @Test
    fun `addField should add more than one field`() {
        val formValidator = FormValidator()
        formValidator.addField(TextField(getText = {""}))
        formValidator.addField(RadioGroupField { true })
        assertEquals(formValidator.formFields.size, 2)
    }

}