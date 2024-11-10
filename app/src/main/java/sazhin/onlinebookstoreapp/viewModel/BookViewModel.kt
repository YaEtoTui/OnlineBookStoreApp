package sazhin.onlinebookstoreapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.urfu.consecutivepractice.coroutinesUtils.launchLoadingAndError
import sazhin.onlinebookstoreapp.domain.IBookRepository
import sazhin.onlinebookstoreapp.domain.models.Book
import sazhin.onlinebookstoreapp.viewModel.state.ListState

class BookViewModel(
    private val repository: IBookRepository
) : ViewModel() {

    private val mutableState = MutableListState()
    val viewState = mutableState as ListState

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.loading = it }
        ) {

            mutableState.items = emptyList()
            mutableState.error = null

            mutableState.items = repository.getBooks()
                .take(viewState.countBooks)
        }
    }

    private class MutableListState : ListState {
        override var items: List<Book> by mutableStateOf(emptyList())
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
        override var countBooks: Int by mutableIntStateOf(5)
    }
}