package com.warmpot.android.formvalidationtdd.validator

data class ValidationResult(
    val field: FormField,
    val error: String? = null
)