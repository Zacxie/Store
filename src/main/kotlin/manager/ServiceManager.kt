package com.bookstore.application.manager

import com.bookstore.application.db.getJdbi
import com.bookstore.application.service.BookService

class ServiceManager {
    private val jdbi = getJdbi()

    val bookService by lazy { BookService(jdbi) }
}
