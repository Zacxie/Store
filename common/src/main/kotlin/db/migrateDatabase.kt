package db

import org.flywaydb.core.Flyway

fun migrateDatabase() {
    var jdbcUrlEnv = System.getenv("JDBC_DATABASE_URL") ?: throw IllegalStateException("JDBC_DATABASE_URL is not set")
    var usernameEnv =
        System.getenv("JDBC_DATABASE_USER") ?: throw IllegalStateException("JDBC_DATABASE_USER is not set")
    var passwordEnv =
        System.getenv("JDBC_DATABASE_PASSWORD") ?: throw IllegalStateException("JDBC_DATABASE_PASSWORD is not set")
    val flyway = Flyway.configure().dataSource(
        jdbcUrlEnv, usernameEnv, passwordEnv
    ).load()
    flyway.migrate()
}
