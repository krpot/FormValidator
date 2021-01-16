package com.warmpot.android.formvalidationtdd.validator

interface FormFieldValidator {
    val message: String
    fun validate(field: FormField): ValidationResult
}