package com.warmpot.android.formvalidationtdd.validator

import android.text.Editable
import android.text.TextWatcher

class TextChangedListener(
    private val formField: FormField
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        validate()
    }

    private fun validate() {
        formField.onShowError(formField.validate()?.error)
    }
}