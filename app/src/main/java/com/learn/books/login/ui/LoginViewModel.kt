package com.learn.books.login.ui

import androidx.lifecycle.ViewModel
import com.learn.books.login.domain.LoginValidationResult
import com.learn.books.login.domain.ValidateLoginUseCase

/**
 * [LoginViewModel] takes the UI inputs/actions and performs required
 */
class LoginViewModel : ViewModel() {

    fun validateUser(username: String, password: String): LoginValidationResult {
       val validateLoginUseCase = ValidateLoginUseCase()
        return validateLoginUseCase.validateLogin(username, password)
    }

}