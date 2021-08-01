package com.learn.books.login.domain

sealed class LoginValidationResult {
    data class Success(val email: String, val password: String) : LoginValidationResult()
    data class EmailNotValid(val message: String): LoginValidationResult()
    data class PasswordNotValid(val message: String): LoginValidationResult()

}