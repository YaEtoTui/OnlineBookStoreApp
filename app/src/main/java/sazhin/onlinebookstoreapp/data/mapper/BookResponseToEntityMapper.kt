package sazhin.onlinebookstoreapp.data.mapper

import sazhin.onlinebookstoreapp.data.model.BookListResponse
import sazhin.onlinebookstoreapp.domain.models.Book

class BookResponseToEntityMapper {
    fun mapBooks(response: BookListResponse): List<Book> {
        return response.books?.map {
            Book(
                id = it?.id ?: 0L,
                name = it?.name.orEmpty(),
                page = it?.page ?: 0,
                price = it?.price ?: 0,
                count = it?.count ?: 0,
                path = it?.path.orEmpty(),
                authorName = it?.authorName.orEmpty()
            )
        }.orEmpty()
    }
}