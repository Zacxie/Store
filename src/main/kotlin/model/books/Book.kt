package com.bookstore.application.model.books

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int,
    val sku: String,
    val price: Double,
    val onSale: Boolean,
    val title: String,
    val author: String,
    val year: Int
)
