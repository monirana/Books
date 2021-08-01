package com.learn.books.home.data.repositories

import com.learn.books.home.data.enitites.mappers.BookApiResponseMapper
import com.learn.books.home.domain.entities.Volume
import com.learn.books.home.domain.repositories.BooksRepository
import com.learn.books.network.apis.BooksApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.learn.books.network.responses.Result

class BooksRepositoryImpl(
    private val mapper: BookApiResponseMapper,
    private val booksApi: BooksApi
) : BooksRepository {

    override suspend fun getBooks(name: String): Result<List<Volume>> =
        withContext(Dispatchers.IO) {
            try {
                val response = booksApi.search(name, 20)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toVolumeList(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

}