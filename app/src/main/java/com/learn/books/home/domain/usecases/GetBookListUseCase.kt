package com.learn.books.home.domain.usecases

import com.learn.books.home.domain.repositories.BooksRepository

class GetBookListUseCase(
    private val booksRepository: BooksRepository
) {
    suspend fun invoke(name: String) = booksRepository.getBooks(name)
}