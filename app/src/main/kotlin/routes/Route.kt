package com.bookstore.application.routes

import com.bookstore.application.dto.healthcheck.HealthCheckDto
import com.bookstore.application.frontend.dashboard.dashboard
import com.bookstore.application.frontend.index.index
import com.bookstore.application.frontend.items.items
import com.bookstore.application.manager.ServiceManager
import com.bookstore.application.routes.books.bookRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body

fun Application.configureRouting(
    services: ServiceManager
) {

    routing {

        get("/healthcheck") {
            call.respond(HealthCheckDto("UP"))
        }

        route("/api") {
            bookRoutes(services.bookService)
        }

        get("/") {
            call.respondHtml(HttpStatusCode.OK) {
                index()
            }
        }

        get("/dashboard") {
            call.respondHtml {
                body { dashboard() }
            }
        }

        get("/items") {
            call.respondHtml {
                body { items() }
            }
        }

    }
}
