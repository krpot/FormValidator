package com.warmpot.android.formvalidationtdd.validator

class TextField(
    val getText: () -> String,
    override val isRequired: () -> Boolean  = { false },
    override val onShowError: (String?) -> Unit,
    override val validators: List<FormFieldValidator> = emptyList(),
) : FormField()
