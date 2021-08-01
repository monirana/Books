package com.learn.books.home.domain.entities

data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val imageUrl: String?
)