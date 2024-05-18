package com.bookstore.application.manager

import com.bookstore.application.service.BookService
import db.getJdbi

class ServiceManager {
    private val jdbi = getJdbi()

    val bookService by lazy { BookService(jdbi) }
}
