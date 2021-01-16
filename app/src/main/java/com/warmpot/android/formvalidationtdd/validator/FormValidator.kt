package com.warmpot.android.formvalidationtdd.validator

class FormValidator(
    fields: List<FormField> = emptyList()
) {
    val formFields = ArrayList<FormField>(fields)

    fun addField(field: FormField) = apply {
        formFields.add(field)
    }

    fun removeField(field: FormField) = apply {
        formFields.remove(field)
    }

    fun validateForm() {
        for (validationResult in validate()) {
            validationResult.field.onShowError(validationResult.error)
        }
    }

    private fun validate(): List<ValidationResult> {
        return formFields.mapNotNull {
            it.validate()
        }
    }
}
