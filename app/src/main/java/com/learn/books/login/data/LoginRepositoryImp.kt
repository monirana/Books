package com.learn.books.login.data

import com.learn.books.login.domain.LoginRepository

/**
 * This class[LoginRepositoryImp] is used to perform login operations.
 * You can add network call here to perform login.
 */
class LoginRepositoryImp: LoginRepository {

    override suspend fun performLogin(email: String, password: String) {
    //    TODO("Add the api hit/localdb authentication here")
    }
}