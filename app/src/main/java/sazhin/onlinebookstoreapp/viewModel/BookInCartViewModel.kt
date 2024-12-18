package sazhin.onlinebookstoreapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sazhin.onlinebookstoreapp.domain.IBookRepository
import sazhin.onlinebookstoreapp.domain.models.Book
import sazhin.onlinebookstoreapp.viewModel.state.BooksInCartState

class BookInCartViewModel(
    private val repository: IBookRepository
) : ViewModel() {

    private var booksInCart: List<Book> = emptyList()

    private val mutableState = MutableBooksState()
    val viewState = mutableState as BooksInCartState

    fun loadBooksInCart() {
        viewModelScope.launch {
            booksInCart = repository.getBooksInCart()
            mutableState.items = booksInCart
        }
    }

    /**
     * Создаем заказ из списка книг в корзине
     */
    fun onCreateOrder() {
        viewModelScope.launch {
            repository.saveOrder(booksInCart)
            loadBooksInCart()
        }
    }

    /**
     * Подсчитываем сумму за всех книги в корзине
     */
    fun checkSumForAllBook(): Int {
        return mutableState.items.sumOf { it.price }
    }

    /**
     * Удаляем книгу из корзины
     */
    fun deleteBook(book: Book) {
        viewModelScope.launch {
            repository.deleteBookInCart(book)
            loadBooksInCart()
        }
    }

    private class MutableBooksState : BooksInCartState {
        override var items: List<Book> by mutableStateOf(emptyList())
    }
}