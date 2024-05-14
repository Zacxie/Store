package routes.books

import com.bookstore.application.dao.BooksDao
import com.bookstore.application.model.books.Book
import com.bookstore.application.routes.books.bookRoutes
import com.bookstore.application.service.BookService
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jdbi.v3.core.Jdbi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BooksRouteTest {

    private val booksDao = mockk<BooksDao>()
    private val jdbi = mockk<Jdbi>()
    private lateinit var bookService: BookService

    @BeforeEach
    fun setUp() {
        clearAllMocks()
        every { jdbi.onDemand(BooksDao::class.java) } returns booksDao
        bookService = BookService(jdbi)
    }

    @Test
    fun `test get all books route`() = testApplication {
        val expectedBooks = listOf(
            Book(1, "SKU1984", 9.99, true, "1984", "George Orwell", 1949),
            Book(2, "SKU1960", 12.99, false, "To Kill a Mockingbird", "Harper Lee", 1960)
        )
        every { booksDao.getAllBooks() } returns expectedBooks

        application {
            install(ContentNegotiation) {
                json()
            }
            routing {
                bookRoutes(bookService)
            }
        }

        val response = client.get("/books") {
            header(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(Json.encodeToString(expectedBooks), response.bodyAsText())

        verify { booksDao.getAllBooks() }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2])
    fun `test get book by id route with valid id`(bookId: Int) = testApplication {
        val expectedBooks = listOf(
            Book(1, "SKU1984", 9.99, true, "1984", "George Orwell", 1949),
            Book(2, "SKU1960", 12.99, false, "To Kill a Mockingbird", "Harper Lee", 1960)
        )

        val expectedBook = expectedBooks.first { it.id == bookId }
        every { booksDao.getBookById(bookId) } returns expectedBook

        application {
            install(ContentNegotiation) {
                json()
            }
            routing {
                bookRoutes(bookService)
            }
        }


        val response = client.get("/books/$bookId") {
            header(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(Json.encodeToString(expectedBook), response.bodyAsText())

        verify { booksDao.getBookById(bookId) }
    }

    @ParameterizedTest
    @ValueSource(ints = [999, 1000])
    fun `test get book by id route with invalid id`(bookId: Int) = testApplication {
        every { booksDao.getBookById(bookId) } returns null

        application {
            install(ContentNegotiation) {
                json()
            }
            routing {
                bookRoutes(bookService)
            }
        }

        val response = client.get("/books/$bookId") {
            header(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals("{\"error\":\"No book found with id $bookId\"}", response.bodyAsText())

        verify { booksDao.getBookById(bookId) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1984", "To Kill a Mockingbird"])
    fun `test get book by title route with valid title`(title: String) = testApplication {
        val expectedBooks = listOf(
            Book(1, "SKU1984", 9.99, true, "1984", "George Orwell", 1949),
            Book(2, "SKU1960", 12.99, false, "To Kill a Mockingbird", "Harper Lee", 1960)
        )

        every { booksDao.getBookByTitle(title) } returns (expectedBooks)

        application {
            install(ContentNegotiation) {
                json()
            }
            routing {
                bookRoutes(bookService)
            }
        }

        val response = client.get("/books/title/$title") {
            header(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(Json.encodeToString(expectedBooks), response.bodyAsText())

        verify { booksDao.getBookByTitle(title) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["NonexistentBook", "UnknownBook"])
    fun `test get book by title route with invalid title`(title: String) = testApplication {
        every { booksDao.getBookByTitle(title) } returns emptyList()

        application {
            install(ContentNegotiation) {
                json()
            }
            routing {
                bookRoutes(bookService)
            }
        }

        val response = client.get("/books/title/$title") {
            header(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }

        assertEquals(HttpStatusCode.OK, response.status)

        verify { booksDao.getBookByTitle(title) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["abc", "123abc"])
    fun `test get book by id route with invalid id format`(id: String) = testApplication {
        application {
            install(ContentNegotiation) {
                json()
            }
            routing {
                bookRoutes(bookService)
            }
        }

        val response = client.get("/books/$id") {
            header(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }

        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals("{\"error\":\"Invalid or missing id\"}", response.bodyAsText())
    }

}
