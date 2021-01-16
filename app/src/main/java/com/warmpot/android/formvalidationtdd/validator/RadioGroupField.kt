package com.warmpot.android.formvalidationtdd.validator

class RadioGroupField(
    override val isRequired: () -> Boolean = { false },
    val isChecked: () -> Boolean,
    override val onShowError: (String?) -> Unit,
    override val validators: List<FormFieldValidator> = emptyList(),
) : FormField()