package com.warmpot.android.formvalidationtdd.validator

class RadioGroupField(
    val isChecked: () -> Boolean,
    override val isRequired: () -> Boolean = { false },
    override val onShowError: (String?) -> Unit,
    override val validators: List<FormFieldValidator> = emptyList(),
) : FormField()