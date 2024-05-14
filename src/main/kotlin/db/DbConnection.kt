package com.bookstore.application.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin

fun getJdbi(): Jdbi {

    val jdbcUrlEnv = System.getenv("JDBC_DATABASE_URL") ?: throw IllegalStateException("JDBC_DATABASE_URL is not set")
    val usernameEnv =
        System.getenv("JDBC_DATABASE_USER") ?: throw IllegalStateException("JDBC_DATABASE_USER is not set")
    val passwordEnv =
        System.getenv("JDBC_DATABASE_PASSWORD") ?: throw IllegalStateException("JDBC_DATABASE_PASSWORD is not set")

    val config = HikariConfig().apply {
        jdbcUrl = jdbcUrlEnv
        username = usernameEnv
        password = passwordEnv
    }
    val dataSource = HikariDataSource(config)
    return Jdbi.create(dataSource).installPlugin(SqlObjectPlugin()).installPlugin(KotlinPlugin())
}
