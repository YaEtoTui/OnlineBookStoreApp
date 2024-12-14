package sazhin.onlinebookstoreapp.viewModel.state

import androidx.compose.runtime.Stable
import sazhin.onlinebookstoreapp.domain.models.Book

@Stable
interface BooksInCartState {
    val items: List<Book>
}