package sazhin.onlinebookstoreapp.data.model

import androidx.annotation.Keep

@Keep
data class BookListResponse(
    val books: List<BookResponse?>?
)