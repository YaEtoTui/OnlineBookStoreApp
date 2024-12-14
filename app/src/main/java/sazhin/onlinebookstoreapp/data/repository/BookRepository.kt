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

    override suspend fun saveBook(book: Book) {
        return withContext(Dispatchers.IO) {
            bookDb.booksDao().insert(
                BookDbEntity(
                    name = book.name,
                    page = book.page,
                    pathPhoto = book.path,
                    price = book.price,
                    authorName = book.authorName
                )
            )
        }
    }

    override suspend fun getBooksInCart(): List<Book> {
        return withContext(Dispatchers.IO) {
            bookDb.booksDao()
                .getAll()
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
}