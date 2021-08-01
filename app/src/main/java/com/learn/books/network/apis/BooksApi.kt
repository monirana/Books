package com.learn.books.network.apis

import com.learn.books.network.responses.BooksListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * [BooksApi] is used to get list of books from server
 *
 */
interface BooksApi {

    @GET("books/v1/volumes")
    suspend fun search(@Query("q") query: String, @Query("maxResults") maxResult: Int): Response<BooksListResponse>

}