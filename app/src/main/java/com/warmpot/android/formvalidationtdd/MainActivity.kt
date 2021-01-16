package com.warmpot.android.formvalidationtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import com.warmpot.android.formvalidationtdd.databinding.ActivityMainBinding
import com.warmpot.android.formvalidationtdd.validator.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViewListeners()
    }

    // region form fields
    private val usernameField by lazy(LazyThreadSafetyMode.NONE) {
        binding.run {
            TextField(
                getText = { usernameEdt.text() },
                isRequired = { true },
                onShowError = { error -> usernameTil.error = error },
                validators = listOf(
                    RequiredFieldValidator(
                        message = getString(R.string.username_required_message)
                    )
                )
            )
        }
    }

    private val emailField by lazy(LazyThreadSafetyMode.NONE) {
        binding.run {
            TextField(
                getText = { emailEdt.text() },
                isRequired = { emailRbtn.isChecked },
                onShowError = { error -> emailTil.error = error },
                validators = listOf(
                    RequiredFieldValidator(
                        message = getString(R.string.email_required_message),
                    ),
                    PatternFieldValidator(
                        pattern = PatternFieldValidator.EMAIL_PATTERN,
                        message = getString(R.string.email_invalid_format_message)
                    )
                )
            )
        }
    }

    private val mobileField by lazy(LazyThreadSafetyMode.NONE) {
        binding.run {
            TextField(
                getText = { mobileEdt.text() },
                isRequired = { mobileRbtn.isChecked },
                onShowError = { error -> mobileTil.error = error },
                validators = listOf(
                    RequiredFieldValidator(
                        message = getString(R.string.mobile_required_message)
                    ),
                    PatternFieldValidator(
                        pattern = PatternFieldValidator.AUSTRALIAN_MOBILE_PATTERN,
                        message = getString(R.string.mobile_invalid_format_message)
                    )
                )
            )
        }
    }

    private val contactMethodField by lazy(LazyThreadSafetyMode.NONE) {
        binding.run {
            RadioGroupField(
                isRequired = { true },
                isChecked = { !preferredContactMethodGroup.nothingSelected() },
                onShowError = { error -> contactMethodErrorTxt.text = error },
                validators = listOf(
                    RequiredFieldValidator(
                        message = getString(R.string.contact_method_select_message)
                    )
                )
            )
        }
    }
    // endregion

    private val formValidator by lazy(LazyThreadSafetyMode.NONE) {
        FormValidator(
            listOf(
                usernameField,
                emailField,
                mobileField,
                contactMethodField
            )
        )
    }

    private fun initViewListeners() = binding.apply {
        usernameEdt.addTextChangedListener(TextChangedListener(usernameField))
        emailEdt.addTextChangedListener(TextChangedListener(emailField))
        mobileEdt.addTextChangedListener(TextChangedListener(mobileField))
        preferredContactMethodGroup.setOnCheckedChangeListener { _, _ ->
            formValidator.validateForm()
        }

        saveBtn.setOnClickListener {
            formValidator.validateForm()
        }
    }
}

fun EditText.text() = text.toString()

fun RadioGroup.nothingSelected(): Boolean = this.checkedRadioButtonId < 0