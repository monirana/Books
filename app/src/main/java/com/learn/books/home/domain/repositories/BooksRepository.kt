package com.learn.books.home.domain.repositories

import com.learn.books.home.domain.entities.Volume
import com.learn.books.network.responses.Result

interface BooksRepository {

    suspend fun getBooks(name: String): Result<List<Volume>>
}