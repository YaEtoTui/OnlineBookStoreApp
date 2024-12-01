package sazhin.onlinebookstoreapp.domain

import sazhin.onlinebookstoreapp.domain.models.Book

interface IBookRepository {

    suspend fun getBooks(): List<Book>

    suspend fun getBooksInCart(): List<Book>
}