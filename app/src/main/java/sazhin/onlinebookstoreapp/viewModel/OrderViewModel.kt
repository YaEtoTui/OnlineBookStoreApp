package sazhin.onlinebookstoreapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sazhin.onlinebookstoreapp.domain.IBookRepository
import sazhin.onlinebookstoreapp.domain.models.Book
import sazhin.onlinebookstoreapp.viewModel.state.OrderState

class OrderViewModel(
    private val repository: IBookRepository
) : ViewModel() {

    private var orders: List<Book> = emptyList()

    private val mutableState = MutableOrdersState()
    val viewState = mutableState as OrderState

    fun loadOrders() {
        viewModelScope.launch {
            orders = repository.getOrders()
            mutableState.items = orders
        }
    }

    fun checkCountOrders(): Int {
        return orders.size
    }

    private class MutableOrdersState : OrderState {
        override var items: List<Book> by mutableStateOf(emptyList())
    }
}