plugins {
    kotlin("jvm") version libs.versions.kotlin.jvm.get()
    kotlin("plugin.serialization") version libs.versions.kotlin.serialization.get()
    id("com.github.johnrengelman.shadow") version libs.versions.shadow.get()
    application
}

group = "com.bookstore"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))

    implementation("io.ktor:ktor-server-core:${libs.versions.ktor.get()}")
    implementation("io.ktor:ktor-server-netty:${libs.versions.ktor.get()}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${libs.versions.ktor.get()}")
    implementation("io.ktor:ktor-server-content-negotiation:${libs.versions.ktor.get()}")
    implementation("io.ktor:ktor-server-html-builder:${libs.versions.ktor.get()}")
    implementation("org.postgresql:postgresql:${libs.versions.postgresql.get()}")
    implementation("org.jdbi:jdbi3-core:${libs.versions.jdbi.get()}")
    implementation("org.jdbi:jdbi3-sqlobject:${libs.versions.jdbi.get()}")
    implementation("org.jdbi:jdbi3-kotlin:${libs.versions.jdbi.get()}")
    implementation("com.zaxxer:HikariCP:${libs.versions.hikaricp.get()}")
    implementation("ch.qos.logback:logback-classic:${libs.versions.logback.get()}")
    implementation("org.jetbrains.kotlinx:kotlinx-html:${libs.versions.kotlinx.html.get()}")

    testImplementation("io.ktor:ktor-server-tests:${libs.versions.ktor.get()}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${libs.versions.junit.get()}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${libs.versions.junit.get()}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${libs.versions.junit.get()}")
    testImplementation("io.mockk:mockk:${libs.versions.mockk.get()}")
    testImplementation("io.ktor:ktor-client-content-negotiation:${libs.versions.ktor.get()}")
    testImplementation("io.zonky.test:embedded-postgres:${libs.versions.embedded.postgres.get()}")
    testImplementation("org.flywaydb:flyway-core:${libs.versions.flyway.get()}")

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
