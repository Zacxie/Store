plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "Store"

include(":app")
include("common")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("jdbi", "3.23.0")
            version("ktor", "2.3.11")
            version("junit", "5.8.1")
            version("mockk", "1.12.0")
            version("embedded-postgres", "1.3.1")
            version("logback", "1.4.12")
            version("postgresql", "42.7.3")
            version("hikaricp", "4.0.3")
            version("flyway", "10.13.0")
            version("kotlin-jvm", "1.9.23")
            version("kotlin-serialization", "1.9.23")
            version("shadow", "7.1.2")
            version("kotlinx-html", "0.9.1")
        }
    }
}
