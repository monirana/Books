package com.learn.books.login.domain

/**
 *[LoginRepository] for authenticating the user
 *
 */

public interface LoginRepository {
    suspend fun performLogin(email: String, password: String)
}