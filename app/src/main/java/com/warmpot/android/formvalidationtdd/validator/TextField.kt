package com.warmpot.android.formvalidationtdd.validator

class TextField(
    override val isRequired: () -> Boolean  = { false },
    val getText: () -> String,
    override val onShowError: (String?) -> Unit,
    override val validators: List<FormFieldValidator> = emptyList(),
) : FormField()
