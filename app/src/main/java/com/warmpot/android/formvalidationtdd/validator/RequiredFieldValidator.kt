package com.warmpot.android.formvalidationtdd.validator

class RequiredFieldValidator(
    override val message: String = "The field must be entered"
): FormFieldValidator {
    override fun validate(field: FormField): ValidationResult {
        return when (field) {
            is TextField -> validateTextField(field)
            is RadioGroupField -> validateRadioGroupField(field)
            else -> throw IllegalArgumentException("Unsupported form field : $field")
        }
    }

    private fun validateTextField(field: TextField): ValidationResult {
        if (field.isRequired() && field.getText().isBlank()) {
            return ValidationResult(field, message)
        }

        return ValidationResult(field, null)
    }

    private fun validateRadioGroupField(field: RadioGroupField): ValidationResult {
        if (field.isRequired() && !field.isChecked()) {
            return ValidationResult(field, message)
        }

        return ValidationResult(field, null)
    }
}
