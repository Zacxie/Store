package dao

import com.bookstore.application.dao.BooksDao
import com.bookstore.application.model.books.BookDto
import io.zonky.test.db.postgres.embedded.EmbeddedPostgres
import org.flywaydb.core.Flyway
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BooksDaoTest {

    private lateinit var jdbi: Jdbi
    private lateinit var booksDao: BooksDao
    private lateinit var postgres: EmbeddedPostgres

    @BeforeAll
    fun setUp() {
        postgres = EmbeddedPostgres.start()

        val jdbcUrl = postgres.getJdbcUrl("postgres", "postgres")
        val flyway = Flyway.configure()
            .dataSource(jdbcUrl, "postgres", "postgres")
            .load()

        flyway.clean()
        flyway.migrate()

        jdbi = Jdbi.create(jdbcUrl, "postgres", "postgres")
            .installPlugin(SqlObjectPlugin()).installPlugin(KotlinPlugin())

        booksDao = jdbi.onDemand(BooksDao::class.java)
    }

    @AfterAll
    fun tearDown() {
        postgres.close()
    }

    @Test
    fun `when getAllBooks is called, then a list of all books is returned`() {
        val expectedBookDtos = listOf(
            BookDto(1, "SKU1984", 9.99, true, "1984", "George Orwell", 1949),
            BookDto(2, "SKU1960", 12.99, false, "To Kill a Mockingbird", "Harper Lee", 1960),
            BookDto(3, "SKU1925", 10.99, true, "The Great Gatsby", "F. Scott Fitzgerald", 1925),
            BookDto(4, "SKU1813", 8.99, false, "Pride and Prejudice", "Jane Austen", 1813),
            BookDto(5, "SKU1961", 11.99, true, "Catch-22", "Joseph Heller", 1961)
        )


        val actualBooks = booksDao.getAllBooks()

        assertNotNull(actualBooks)
        assertEquals(expectedBookDtos, actualBooks)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5])
    fun `when getBookById is called with a valid id, then the corresponding book is returned`(bookId: Int) {
        val expectedBookDtos = listOf(
            BookDto(1, "SKU1984", 9.99, true, "1984", "George Orwell", 1949),
            BookDto(2, "SKU1960", 12.99, false, "To Kill a Mockingbird", "Harper Lee", 1960),
            BookDto(3, "SKU1925", 10.99, true, "The Great Gatsby", "F. Scott Fitzgerald", 1925),
            BookDto(4, "SKU1813", 8.99, false, "Pride and Prejudice", "Jane Austen", 1813),
            BookDto(5, "SKU1961", 11.99, true, "Catch-22", "Joseph Heller", 1961)
        )


        val expectedBook = expectedBookDtos.first { it.id == bookId }

        val actualBook = booksDao.getBookById(bookId)

        assertNotNull(actualBook)
        assertEquals(expectedBook, actualBook)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1984", "To Kill a Mockingbird", "The Great Gatsby", "Pride and Prejudice", "Catch-22"])
    fun `when getBookByTitle is called with a valid title, then the corresponding book is returned`(title: String) {
        val expectedBookDtos = listOf(
            BookDto(1, "SKU1984", 9.99, true, "1984", "George Orwell", 1949),
            BookDto(2, "SKU1960", 12.99, false, "To Kill a Mockingbird", "Harper Lee", 1960),
            BookDto(3, "SKU1925", 10.99, true, "The Great Gatsby", "F. Scott Fitzgerald", 1925),
            BookDto(4, "SKU1813", 8.99, false, "Pride and Prejudice", "Jane Austen", 1813),
            BookDto(5, "SKU1961", 11.99, true, "Catch-22", "Joseph Heller", 1961)
        )


        val expectedBook = expectedBookDtos.first { it.title == title }

        val actualBook = booksDao.getBookByTitle(title)

        assertNotNull(actualBook)
        assertEquals(listOf(expectedBook), actualBook)
    }

}
