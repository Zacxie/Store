package com.bookstore.application.service

import com.bookstore.application.dao.BooksDao
import com.bookstore.application.exceptions.BookNotFoundException
import com.bookstore.application.dto.books.BookDto
import org.jdbi.v3.core.Jdbi

class BookService(
    jdbi: Jdbi
) {
    private val booksDao = jdbi.onDemand(BooksDao::class.java)

    fun getAllBooks(): List<BookDto?> {
        return booksDao.getAllBooks()
    }

    fun getBookById(id: Int): BookDto {
        return booksDao.getBookById(id) ?: throw BookNotFoundException("No book found with id $id")
    }

    fun getBookByTitle(title: String): List<BookDto?> {
        return booksDao.getBookByTitle(title)
    }


}