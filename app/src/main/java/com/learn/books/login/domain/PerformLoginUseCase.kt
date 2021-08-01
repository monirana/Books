package com.learn.books.login.usecases

import com.learn.books.login.domain.LoginRepository


/**
 * [PerformLoginUseCase] authenticates the credentials provided by user
 *
 * For authenticating the user using server or local database
 */
class PerformLoginUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String) =
        loginRepository.performLogin(email, password)
}