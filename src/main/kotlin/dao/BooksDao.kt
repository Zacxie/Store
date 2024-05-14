package com.bookstore.application.dao

import com.bookstore.application.model.books.Book
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery

interface BooksDao {
    @SqlQuery("SELECT * FROM books")
    fun getAllBooks(): List<Book?>

    @SqlQuery("SELECT * FROM books WHERE id = :id")
    fun getBookById(@Bind("id") id: Int): Book?

    @SqlQuery("SELECT * FROM books WHERE title = :title")
    fun getBookByTitle(@Bind("title") title: String): List<Book?>
}
