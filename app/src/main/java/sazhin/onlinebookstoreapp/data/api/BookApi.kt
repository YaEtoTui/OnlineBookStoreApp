package sazhin.onlinebookstoreapp.data.api

import retrofit2.http.GET
import sazhin.onlinebookstoreapp.data.model.BookResponse

interface BookApi {

    @GET("api/books/list")
    suspend fun getBooks(): List<BookResponse?>?

    @GET("api/books/cart/list")
    suspend fun getBooksInCart(): List<BookResponse?>?
}