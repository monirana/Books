package com.learn.books.home.ui

import androidx.lifecycle.*
import com.learn.books.home.data.repositories.BooksRepositoryImpl
import com.learn.books.home.data.enitites.mappers.BookApiResponseMapper
import com.learn.books.home.domain.entities.Volume
import com.learn.books.home.domain.repositories.BooksRepository
import com.learn.books.home.domain.usecases.GetBookListUseCase
import com.learn.books.network.RetrofitClient
import com.learn.books.network.responses.Result
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _books = MutableLiveData<List<Volume>>()
    val books = _books

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var booksRepository: BooksRepository

    init {
        val retrofitClient = RetrofitClient().getInstance()
        booksRepository = BooksRepositoryImpl(
            mapper = BookApiResponseMapper(),
            booksApi = retrofitClient!!.provideBooksService()
        )
    }

    public fun getBooks(name: String) {
        val getBookListUseCase = GetBookListUseCase(booksRepository)
        _isLoading.postValue(true)
        viewModelScope.launch {
            when (val booksResponse = getBookListUseCase.invoke(name)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    books.value = booksResponse.data
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _error.postValue(booksResponse.exception.message)
                }

            }
        }
    }


}