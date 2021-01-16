package com.warmpot.android.formvalidationtdd.validator

import org.intellij.lang.annotations.RegExp

class PatternFieldValidator(
    pattern: String,
    override val message: String = "Invalid input format"
) : FormFieldValidator {

    companion object {
        const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        const val AUSTRALIAN_MOBILE_PATTERN =
            "^(?:\\+?(61))? ?(?:\\((?=.*\\)))?(0?[2-57-8])\\)? ?(\\d\\d(?:[- ](?=\\d{3})|(?!\\d\\d[- ]?\\d[- ]))\\d\\d[- ]?\\d[- ]?\\d{3})\$"
    }

    private val regEx = Regex(pattern)

    override fun validate(field: FormField): ValidationResult {
        if (field !is TextField) {
            return ValidationResult(field, null)
        }

        if (field.getText().isBlank()) {
            return ValidationResult(field, null)
        }

        if (regEx.matches(field.getText())) {
            return ValidationResult(field, null)
        }

        return ValidationResult(field, message)
    }

}