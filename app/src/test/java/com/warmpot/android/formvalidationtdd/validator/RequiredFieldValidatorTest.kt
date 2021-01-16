package com.warmpot.android.formvalidationtdd.validator

import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Test

class RequiredFieldValidatorTest {

    private var text = ""
    private var isChecked = false

    @Test
    fun `validate WHEN text required and empty THEN should return fail`() {
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(TextField(isRequired = { true }, getText = { text }, onShowError = {}))
        assertNotNull(result.error)
    }

    @Test
    fun `validate WHEN text required and blank THEN should not return fail`() {
        text = " "
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(TextField(isRequired = { false }, getText = { text }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate WHEN text required and single char THEN should not return fail`() {
        text = "a"
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(TextField(isRequired = { false }, getText = { text }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate WHEN text required and long char THEN should not return fail`() {
        text = "abcd ere"
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(TextField(isRequired = { false }, getText = { text }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate WHEN text not required THEN should not return fail`() {
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(TextField(isRequired = { false }, getText = { text }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate WHEN radioGroup required and notChecked THEN should return fail`() {
        isChecked = false
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(RadioGroupField(isRequired = { true }, isChecked = { isChecked }, onShowError = {}))
        assertNotNull(result.error)
    }

    @Test
    fun `validate WHEN radioGroup required and checked THEN should not return fail`() {
        isChecked = true
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(RadioGroupField(isRequired = { true }, isChecked = { isChecked }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate WHEN radioGroup not required and notChecked THEN should not return fail`() {
        isChecked = false
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(RadioGroupField(isRequired = { false }, isChecked = { isChecked }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate WHEN radioGroup not required and checked THEN should not return fail`() {
        isChecked = true
        val validator = RequiredFieldValidator()
        val result: ValidationResult =
            validator.validate(RadioGroupField(isRequired = { false }, isChecked = { isChecked }, onShowError = {}))
        assertNull(result.error)
    }
}