# Kotlin API with PostgreSQL

This is a Kotlin application that utilizes a PostgreSQL database for storing data and exposes API endpoints. The purpose of this project is to provide a learning example of how a Kotlin API could look like.

## Endpoints

- **GET /api/books**: Returns all books.
- **GET /api/books/{id}**: Returns a specific book by its ID.
- **GET /api/books/title/{title}**: Returns a list of all books with the given title.
- **GET /healthcheck**: Returns a health check status in JSON format.

## Prerequisites

- Docker
- Docker Compose

## Getting Started

To run the application, ensure you have Docker installed. Follow these steps:

1. Build the Docker image:

   ```sh
   docker-compose build
   ```

2. Run the Docker containers:

   ```sh
   docker-compose up
   ```

The application will be accessible at [http://localhost:8080](http://localhost:8080).

## Built With

- [Kotlin](https://kotlinlang.org/) - Programming language
- [Ktor](https://ktor.io/) - Kotlin framework for building web applications
- [PostgreSQL](https://www.postgresql.org/) - Database
- [Docker](https://www.docker.com/) - Containerization platform

## Project Structure

This project is structured to demonstrate a clean architecture for a Kotlin application:

- **src/main/kotlin**: Contains the Kotlin source code.
- **src/main/resources**: Contains configuration files.
- **docker-compose.yml**: Docker Compose file for setting up the application and database.
- **build.gradle.kts**: Gradle build file.

## Authors

- [Zacxie](https://github.com/Zacxie)

## License

This project is licensed under the MIT License - see the [LICENSE](https://opensource.org/license/mit) file for details.

## Acknowledgments

- Inspiration and learning resources from various online documentation.

---
