package com.warmpot.android.formvalidationtdd.validator

abstract class FormField {
    abstract val isRequired: () -> Boolean
    abstract val onShowError: (String?) -> Unit
    abstract val validators: List<FormFieldValidator>

    fun validate(): ValidationResult? {
        val validationResult = validators.map { validator ->
            validator.validate(this)
        }

        return validationResult.find { it.error != null }
    }
}
