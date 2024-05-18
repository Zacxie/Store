plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.tr0xy"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jdbi:jdbi3-core:3.23.0")
    implementation("org.jdbi:jdbi3-sqlobject:3.23.0")
    implementation("org.jdbi:jdbi3-kotlin:3.23.0")
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("org.flywaydb:flyway-database-postgresql:10.13.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}