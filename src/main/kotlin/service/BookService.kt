package com.bookstore.application.service

import com.bookstore.application.dao.BooksDao
import com.bookstore.application.exceptions.BookNotFoundException
import com.bookstore.application.model.books.Book
import org.jdbi.v3.core.Jdbi

class BookService(
    jdbi: Jdbi
) {
    private val booksDao = jdbi.onDemand(BooksDao::class.java)

    fun getAllBooks(): List<Book?> {
        return booksDao.getAllBooks()
    }

    fun getBookById(id: Int): Book {
        return booksDao.getBookById(id) ?: throw BookNotFoundException("No book found with id $id")
    }

    fun getBookByTitle(title: String): List<Book?> {
        return booksDao.getBookByTitle(title)
    }


}