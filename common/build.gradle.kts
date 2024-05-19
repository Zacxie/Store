plugins {
    kotlin("jvm") version libs.versions.kotlin.jvm.get()
}

group = "com.bookstore"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jdbi:jdbi3-core:${libs.versions.jdbi.get()}")
    implementation("org.jdbi:jdbi3-sqlobject:${libs.versions.jdbi.get()}")
    implementation("org.jdbi:jdbi3-kotlin:${libs.versions.jdbi.get()}")
    implementation("com.zaxxer:HikariCP:${libs.versions.hikaricp.get()}")
    implementation("org.flywaydb:flyway-database-postgresql:${libs.versions.flyway.get()}")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}