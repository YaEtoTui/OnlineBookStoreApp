package sazhin.onlinebookstoreapp.data.api

import retrofit2.http.GET
import sazhin.onlinebookstoreapp.data.model.BookListResponse

interface BookApi {

    @GET("/api/books/list")
    suspend fun getBooks(): BookListResponse
}