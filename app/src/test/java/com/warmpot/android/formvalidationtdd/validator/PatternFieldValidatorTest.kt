package com.warmpot.android.formvalidationtdd.validator

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class PatternFieldValidatorTest {

    @Test
    fun `validate WHEN not textField THEN skip validation`() {
        val validator = PatternFieldValidator(PatternFieldValidator.EMAIL_PATTERN)
        val result = validator.validate(RadioGroupField(isChecked = { false }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate email WHEN blank text THEN should skip validation`() {
        val validator = PatternFieldValidator(pattern = PatternFieldValidator.EMAIL_PATTERN)
        val result = validator.validate(TextField(getText = { " " }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate email WHEN valid format THEN should be validation success`() {
        val validator = PatternFieldValidator(pattern = PatternFieldValidator.EMAIL_PATTERN)
        val result = validator.validate(TextField(getText = { "abc.def@gmail.com" }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate email WHEN missing @ THEN should be validation failure`() {
        val validator = PatternFieldValidator(pattern = PatternFieldValidator.EMAIL_PATTERN)
        val result = validator.validate(TextField(getText = { "abc.def gmail.com" }, onShowError = {}))
        assertNotNull(result.error)
    }

    @Test
    fun `validate email WHEN missing domain part THEN should be validation failure`() {
        val validator = PatternFieldValidator(pattern = PatternFieldValidator.EMAIL_PATTERN)
        val result = validator.validate(TextField(getText = { "abc@" }, onShowError = {}))
        assertNotNull(result.error)
    }

    @Test
    fun `validate mobile WHEN blank THEN should skip validation`() {
        val validator =
            PatternFieldValidator(pattern = PatternFieldValidator.AUSTRALIAN_MOBILE_PATTERN)
        val result = validator.validate(TextField(getText = { " " }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate mobile WHEN valid format THEN should be validation success`() {
        val validator =
            PatternFieldValidator(pattern = PatternFieldValidator.AUSTRALIAN_MOBILE_PATTERN)
        val result = validator.validate(TextField(getText = { "0432123456" }, onShowError = {}))
        assertNull(result.error)
    }

    @Test
    fun `validate mobile WHEN shor number THEN should be validation failure`() {
        val validator =
            PatternFieldValidator(pattern = PatternFieldValidator.AUSTRALIAN_MOBILE_PATTERN)
        val result = validator.validate(TextField(getText = { "612" }, onShowError = {}))
        assertNotNull(result.error)
    }

    @Test
    fun `validate mobile WHEN long number THEN should be validation failure`() {
        val validator =
            PatternFieldValidator(pattern = PatternFieldValidator.AUSTRALIAN_MOBILE_PATTERN)
        val result = validator.validate(TextField(getText = { "043123456789" }, onShowError = {}))
        assertNotNull(result.error)
    }
}