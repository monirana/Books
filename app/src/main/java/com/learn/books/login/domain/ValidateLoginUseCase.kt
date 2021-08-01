package com.learn.books.login.domain

import android.text.TextUtils


/**
 * This use case class [ValidateLoginUseCase] validates the login fields
 * entered by user and returns LoginValidationResult.
 * Return success if both email and password are not empty
 * Returns email or password error if validation fails
 */
public class ValidateLoginUseCase() {
    fun validateLogin(email: String, password: String): LoginValidationResult {
        return when {
            TextUtils.isEmpty(email) -> LoginValidationResult.EmailNotValid("Cannot be empty")
            TextUtils.isEmpty(password) -> LoginValidationResult.PasswordNotValid("Cannot be empty")
            else -> LoginValidationResult.Success(email, password)
        }
    }

}