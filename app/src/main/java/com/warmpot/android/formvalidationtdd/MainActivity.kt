package com.warmpot.android.formvalidationtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import com.warmpot.android.formvalidationtdd.validator.*

class MainActivity : AppCompatActivity() {

    private val usernameTil: TextInputLayout by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.usernameTil) }
    private val emailTil: TextInputLayout by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.emailTil) }
    private val mobileTil: TextInputLayout by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.mobileTil) }
    private val usernameEdt: EditText by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.usernameEdt) }
    private val emailEdt: EditText by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.emailEdt) }
    private val mobileEdt: EditText by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.mobileEdt) }
    private val contactMethodErrorTxt: TextView by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.contactMethodErrorTxt) }
    private val preferredContactMethodGroup: RadioGroup by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(
            R.id.preferredContactMethodGroup
        )
    }
    private val emailRbtn: RadioButton by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.emailRbtn) }
    private val mobileRbtn: RadioButton by lazy(LazyThreadSafetyMode.NONE) { findViewById(R.id.mobileRbtn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFormValidator()
    }

    private lateinit var formValidator: FormValidator

    private fun initFormValidator() {
        val usernameField = TextField(
            getText = { usernameEdt.text() },
            isRequired = { true },
            onShowError = { error -> usernameTil.error = error },
            validators = listOf(
                RequiredFieldValidator(
                    message = getString(R.string.username_required_message)
                )
            )
        )

        val emailField = TextField(
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

        val mobileField = TextField(
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

        val contactMethodField = RadioGroupField(
            isRequired = { true },
            isChecked = { !preferredContactMethodGroup.nothingSelected() },
            onShowError = { error -> contactMethodErrorTxt.text = error },
            validators = listOf(
                RequiredFieldValidator(
                    message = getString(R.string.contact_method_select_message)
                )
            )
        )

        formValidator = FormValidator(
            listOf(
                usernameField,
                emailField,
                mobileField,
                contactMethodField
            )
        )

        usernameEdt.addTextChangedListener(TextChangedListener(usernameField))
        emailEdt.addTextChangedListener(TextChangedListener(emailField))
        mobileEdt.addTextChangedListener(TextChangedListener(mobileField))

        preferredContactMethodGroup.setOnCheckedChangeListener { _, _ ->
            formValidator.validateForm()
        }

        findViewById<Button>(R.id.saveBtn).setOnClickListener {
            formValidator.validateForm()
        }
    }
}

fun EditText.text() = text.toString()

fun RadioGroup.nothingSelected(): Boolean = this.checkedRadioButtonId < 0