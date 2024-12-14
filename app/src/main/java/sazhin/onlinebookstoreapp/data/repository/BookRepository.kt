package sazhin.onlinebookstoreapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sazhin.onlinebookstoreapp.data.api.BookApi
import sazhin.onlinebookstoreapp.data.db.BookDatabase
import sazhin.onlinebookstoreapp.data.mapper.BookResponseToEntityMapper
import sazhin.onlinebookstoreapp.data.model.entity.BookDbEntity
import sazhin.onlinebookstoreapp.domain.IBookRepository
import sazhin.onlinebookstoreapp.domain.models.Book

class BookRepository(
    private val api: BookApi,
    private val bookDb: BookDatabase,
    private val mapper: BookResponseToEntityMapper
) : IBookRepository {

    override suspend fun getBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            mapper.mapBooks(api.getBooks())
        }
    }

    override suspend fun saveBookInCart(book: Book) {
        return withContext(Dispatchers.IO) {
            bookDb.booksDao().insert(
                BookDbEntity(
                    name = book.name,
                    page = book.page,
                    pathPhoto = book.path,
                    price = book.price,
                    authorName = book.authorName,
                    isOrder = false
                )
            )
        }
    }

    override suspend fun saveOrder(bookList: List<Book>) {
        return withContext(Dispatchers.IO) {
            bookList.forEach { book ->
                bookDb.booksDao().update(
                    BookDbEntity(
                        id = book.id,
                        name = book.name,
                        page = book.page,
                        pathPhoto = book.path,
                        price = book.price,
                        authorName = book.authorName,
                        isOrder = true
                    )
                )
            }
        }
    }

    override suspend fun getBooksInCart(): List<Book> {
        return withContext(Dispatchers.IO) {
            bookDb.booksDao()
                .getAllBookInCart()
                .map {
                    Book(
                        id = it.id ?: 0L,
                        name = it.name.orEmpty(),
                        page = it.page ?: 0,
                        price = it.price ?: 0,
                        count = 0,
                        path = it.pathPhoto.orEmpty(),
                        authorName = it.authorName.orEmpty()
                    )
                }
        }
    }

    override suspend fun getOrders(): List<Book> {
        return withContext(Dispatchers.IO) {
            bookDb.booksDao()
                .getAllOrders()
                .map {
                    Book(
                        id = it.id ?: 0L,
                        name = it.name.orEmpty(),
                        page = it.page ?: 0,
                        price = it.price ?: 0,
                        count = 0,
                        path = it.pathPhoto.orEmpty(),
                        authorName = it.authorName.orEmpty()
                    )
                }
        }
    }

    override suspend fun deleteBookInCart(book: Book) {
        return withContext(Dispatchers.IO) {
            bookDb.booksDao().delete(
                BookDbEntity(
                    id = book.id,
                    name = book.name,
                    page = book.page,
                    pathPhoto = book.path,
                    price = book.price,
                    authorName = book.authorName,
                    isOrder = false
                )
            )
        }
    }
}