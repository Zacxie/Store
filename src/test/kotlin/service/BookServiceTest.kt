package service

import com.bookstore.application.dao.BooksDao
import com.bookstore.application.exceptions.BookNotFoundException
import com.bookstore.application.model.books.Book
import com.bookstore.application.service.BookService
import io.mockk.every
import io.mockk.mockk
import org.jdbi.v3.core.Jdbi
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BookServiceTest {

    private lateinit var jdbi: Jdbi
    private lateinit var booksDao: BooksDao
    private lateinit var bookService: BookService

    @BeforeEach
    fun setUp() {
        jdbi = mockk()
        booksDao = mockk()

        every { jdbi.onDemand(BooksDao::class.java) } returns booksDao

        bookService = BookService(jdbi)
    }

    @Test
    fun `when getAllBooks() is called, a list of all books are returned`() {
        val expectedBooks = listOf(
            Book(1, "SKU1984", 9.99, true, "1984", "George Orwell", 1949),
            Book(2, "SKU1960", 12.99, false, "To Kill a Mockingbird", "Harper Lee", 1960)
        )

        every { booksDao.getAllBooks() } returns expectedBooks

        val actualBooks = bookService.getAllBooks()

        assertNotNull(actualBooks)
        assertEquals(expectedBooks.size, actualBooks.size)
        assertEquals(expectedBooks, actualBooks)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun `when getBookById is called with a valid ids, then a single book with the corresponding id is returned`(id: Int) {
        val expectedBook = Book(id, "SKU0001", 10.00, false, "Sample Book", "Sample Author", 2022)
        every { booksDao.getBookById(id) } returns expectedBook

        val actualBook = bookService.getBookById(id)

        assertNotNull(actualBook)
        assertEquals(expectedBook, actualBook)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1984", "Animal Farm"])
    fun `when getBookByTitle is called with valid titles, then the corresponding book is returned`(title: String) {
        val expectedBook =
            Book(1, "SKU1984", 9.99, true, "1984", "George Orwell", 1949)

        every { booksDao.getBookByTitle(title) } returns listOf(expectedBook)

        val actualBook = bookService.getBookByTitle(title)

        assertNotNull(actualBook)
        assertEquals(listOf(expectedBook), actualBook)
    }

    @Test
    fun `when getBookById is called with an invalid id, then BookNotFoundException is thrown`() {
        every { booksDao.getBookById(any()) } returns null

        assertThrows(BookNotFoundException::class.java) {
            bookService.getBookById(999)
        }
    }

}
