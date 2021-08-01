package com.learn.books.home.data.enitites.mappers

import com.learn.books.home.domain.entities.Volume
import com.learn.books.home.domain.entities.VolumeInfo
import com.learn.books.network.responses.BooksListResponse


class BookApiResponseMapper {
    fun toVolumeList(response: BooksListResponse): List<Volume> {
        return response.items.map {
            Volume(
                it.id, VolumeInfo(
                    it.volumeInfo.title,
                    it.volumeInfo.authors,
                    it.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
                )
            )
        }
    }
}