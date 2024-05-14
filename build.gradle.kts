plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "com.bookstore"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.11")
    implementation("io.ktor:ktor-server-netty:2.3.11")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.11")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.11")
    implementation("org.flywaydb:flyway-core:7.7.3")
    implementation("org.jdbi:jdbi3-core:3.23.0")
    implementation("org.jdbi:jdbi3-sqlobject:3.23.0")
    implementation("org.jdbi:jdbi3-kotlin:3.23.0")
    implementation("com.zaxxer:HikariCP:4.0.3")

    testImplementation("io.ktor:ktor-server-tests:2.3.11")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("io.ktor:ktor-client-content-negotiation:2.0.0")
    testImplementation("io.zonky.test:embedded-postgres:1.3.1")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.bookstore.application.ApplicationKt"
    }
}
application {
    mainClass.set("com.bookstore.application.ApplicationKt")
}
tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("Store")
        archiveVersion.set("1.0-SNAPSHOT")
        archiveClassifier.set("")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "com.bookstore.application.ApplicationKt"))
        }
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
