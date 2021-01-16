package com.warmpot.android.formvalidationtdd.validator

import org.junit.Assert.*
import org.junit.Test

class FormValidatorTest {

    @Test
    fun `addField should add text field`() {
        val formValidator = FormValidator()
        formValidator.addField(TextField(getText = { "" }, onShowError = {}))
        assertTrue(formValidator.formFields.first() is TextField)
    }

    @Test
    fun `addField should add more than one field`() {
        val formValidator = FormValidator()
        formValidator.addField(TextField(getText = { "" }, onShowError = {}))
        formValidator.addField(RadioGroupField(isChecked = { true }, onShowError = {}))
        assertEquals(formValidator.formFields.size, 2)
    }
}