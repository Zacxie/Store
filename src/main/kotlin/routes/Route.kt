package com.bookstore.application.routes

import com.bookstore.application.manager.ServiceManager
import com.bookstore.application.model.healthcheck.HealthCheck
import com.bookstore.application.routes.books.bookRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    services: ServiceManager
) {

    routing {

        get("/healthcheck") {
            call.respond(HealthCheck("UP"))
        }

        route("/api") {
            bookRoutes(services.bookService)

        }


    }
}
