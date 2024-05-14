package com.bookstore.application.routes.books

import com.bookstore.application.exceptions.BookNotFoundException
import com.bookstore.application.service.BookService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.bookRoutes(bookService: BookService) {

    route("/books") {
        get {
            call.respond(bookService.getAllBooks())
        }
    }

    route("/books/{id}") {
        get {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(
                HttpStatusCode.BadRequest, mapOf(
                    "error" to "Invalid or missing id"
                )
            )
            try {
                call.respond(bookService.getBookById(id))
            } catch (e: BookNotFoundException) {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to e.message))
            }
        }
    }

    route("/books/title/{title}") {
        get {
            val title = call.parameters["title"] ?: return@get call.respond(
                HttpStatusCode.BadRequest, mapOf(
                    "error" to "Invalid or missing title"
                )
            )
            try {
                call.respond(bookService.getBookByTitle(title))
            } catch (e: BookNotFoundException) {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to e.message))
            }
        }
    }
}
