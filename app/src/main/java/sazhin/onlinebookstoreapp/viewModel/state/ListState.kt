package sazhin.onlinebookstoreapp.viewModel.state

import androidx.compose.runtime.Stable
import sazhin.onlinebookstoreapp.domain.models.Book

@Stable
interface ListState {
    val items: List<Book>
    val error: String?
    var loading: Boolean
    var countBooks: Int
}