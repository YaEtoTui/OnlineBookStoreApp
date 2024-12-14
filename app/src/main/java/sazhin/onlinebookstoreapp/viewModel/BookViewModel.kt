package sazhin.onlinebookstoreapp.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.urfu.consecutivepractice.coroutinesUtils.launchLoadingAndError
import sazhin.onlinebookstoreapp.data.model.dto.PreferencesDto
import sazhin.onlinebookstoreapp.domain.IBookRepository
import sazhin.onlinebookstoreapp.domain.models.Book
import sazhin.onlinebookstoreapp.viewModel.state.ListState

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class BookViewModel(
    private val repository: IBookRepository,
    private val context: Context
) : ViewModel() {

    val KEY_COUNT_BOOKS = intPreferencesKey("count_books")
    val counterFlow: Flow<PreferencesDto> = context.dataStore.data
        .map { preferences ->
            val countBooks: Int = preferences[KEY_COUNT_BOOKS] ?: 10000

            return@map PreferencesDto(
                countBooks
            )
        }

    private val mutableState = MutableListState()
    val viewState = mutableState as ListState

    init {
        loadBooks()

        viewModelScope.launch {
            counterFlow.collect {
                setCountBooks(it.countBooks)
            }
        }
    }

    private fun setCountBooks(countBooks: Int) {
        mutableState.countBooks = countBooks
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

    fun updateParameters(countBooks: Int) {
        viewModelScope.launch {
            saveParameters(countBooks)
        }

        changeParameters(countBooks)
    }

    private fun changeParameters(countBooks: Int) {
        mutableState.countBooks = countBooks
    }

    private suspend fun saveParameters(countBooks: Int) {
        context.dataStore.edit { settings ->
            settings[KEY_COUNT_BOOKS] = countBooks
        }
    }

    fun onInCartClicked(book: Book) {
        viewModelScope.launch {
            mutableState.items.find { it.name == book.name }
                ?.let { repository.saveBookInCart(book) }
        }
    }

    private class MutableListState : ListState {
        override var items: List<Book> by mutableStateOf(emptyList())
        override var countBooksInCart: Int by mutableIntStateOf(0)
        override var error: String? by mutableStateOf(null)
        override var loading: Boolean by mutableStateOf(false)
        override var countBooks: Int by mutableIntStateOf(10000)
    }
}