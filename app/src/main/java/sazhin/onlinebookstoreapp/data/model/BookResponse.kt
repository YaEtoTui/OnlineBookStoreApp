package sazhin.onlinebookstoreapp.data.model

import androidx.annotation.Keep

@Keep
data class BookResponse(
    val id: Long?,
    val name: String?,
    val page: Int?,
    val price: Int?,
    val count: Int?,
    val path: String?,
    val authorName: String?,
)