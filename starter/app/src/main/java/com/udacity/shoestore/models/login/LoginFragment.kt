package com.udacity.shoestore.models.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    private val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    //    private val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private val passwordPattern = Regex("^(.*[A-Za-z])")
    private val emailLengthRequirement = 1
    private val passwordLengthRequirement = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        binding.loginButton.setOnClickListener {
            val emailFieldErrorsCheck = checkFieldForErrors(
                binding.textEmailLayout,
                emailPattern,
                emailLengthRequirement,
                "email"
            )
            val passwordField = checkFieldForErrors(
                binding.textPasswordLayout,
                passwordPattern,
                passwordLengthRequirement,
                "password"
            )
            if (!emailFieldErrorsCheck && !passwordField) {
                findNavController().navigate(LoginFragmentDirections.actionLoginToWelcome())
            }
        }
        binding.registerButton.setOnClickListener {
            val emailFieldErrorsCheck = checkFieldForErrors(
                binding.textEmailLayout,
                emailPattern,
                emailLengthRequirement,
                "email"
            )
            val passwordField = checkFieldForErrors(
                binding.textPasswordLayout,
                passwordPattern,
                passwordLengthRequirement,
                "password"
            )
            if (!emailFieldErrorsCheck && !passwordField) {
                findNavController().navigate(LoginFragmentDirections.actionLoginToWelcome())
            }
        }
        return binding.root
    }

    private fun checkFieldForErrors(
        field: TextInputLayout,
        pattern: Regex,
        length: Int,
        label: String
    ): Boolean {
        if (checkEmptyFieldsCheck(field, label)) {
            return true
        }
        if (checkLength(field, length, label)) {
            return true
        }
        if (checkPattern(field, pattern, label)) {
            return true
        }
        return false
    }

    private fun checkEmptyFieldsCheck(field: TextInputLayout, label: String): Boolean {
        if (field.editText?.text.toString().isBlank()) {
            field.error = "$label is required"
            return true
        } else {
            field.error = null
            return false
        }
    }

    private fun checkLength(field: TextInputLayout, length: Int, label: String): Boolean {
        if (field.editText?.text.toString().length < length) {
            field.error = "$label must be at least $length characters long"
            return true
        } else {
            field.error = null
            return false
        }
    }

    private fun checkPattern(field: TextInputLayout, pattern: Regex, label: String): Boolean {
        if (!pattern.matches(field.editText?.text.toString())) {
            field.error = "Invalid $label"
            return true
        } else {
            field.error = null
            return false
        }
    }
}