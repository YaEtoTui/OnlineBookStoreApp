package sazhin.onlinebookstoreapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sazhin.onlinebookstoreapp.data.api.BookApi
import sazhin.onlinebookstoreapp.data.mapper.BookResponseToEntityMapper
import sazhin.onlinebookstoreapp.domain.IBookRepository
import sazhin.onlinebookstoreapp.domain.models.Book

class BookRepository(
    private val api: BookApi,
    private val mapper: BookResponseToEntityMapper
) : IBookRepository {

    override suspend fun getBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            mapper.mapBooks(api.getBooks())
        }
    }

    override suspend fun getBooksInCart(): List<Book> {
        return withContext(Dispatchers.IO) {
            mapper.mapBooks(api.getBooksInCart())
        }
    }
}