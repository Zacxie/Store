package com.bookstore.application.config

import com.bookstore.application.manager.ServiceManager
import com.bookstore.application.routes.configureRouting
import db.migrateDatabase
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.module() {
    val services = ServiceManager()

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }

    migrateDatabase()
    configureRouting(services)

}
