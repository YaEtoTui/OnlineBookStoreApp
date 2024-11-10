package sazhin.onlinebookstoreapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id: Long,
    val name: String,
    val page: Int,
    val price: Int,
    val count: Int,
    val path: String,
    val authorName: String,
) : Parcelable