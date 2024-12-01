package sazhin.onlinebookstoreapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import sazhin.onlinebookstoreapp.domain.models.Book
import sazhin.onlinebookstoreapp.viewModel.BookViewModel
import sazhin.onlinebookstoreapp.viewModel.state.ListState

@Parcelize
class ListBooksScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Suppress("MagicNumber")
    @Composable
    override fun Content(modifier: Modifier) {

        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<BookViewModel>()
        val state = viewModel.viewState

        Column(Modifier.fillMaxSize()) {

            val lazyColumnState = rememberSaveable(saver = LazyListState.Saver) {
                LazyListState(
                    0,
                    0
                )
            }

            state.error?.let {
                Text(text = it)
            }

            LazyColumn(
                Modifier.fillMaxSize(),
                lazyColumnState
            ) {
                items(state.items) {
                    ConstructorItem(item = it, navigation, state)
                }
            }
        }
    }
}

@Composable
private fun ConstructorItem(
    item: Book,
    navigation: StackNavContainer? = null,
    state: ListState
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigation?.forward(BookScreen(item)) }
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isCartVisible by remember { mutableStateOf(false) }

        AsyncImage(
            model = item.path,
            modifier = Modifier
                .size(256.dp),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = String.format("Название книги: %s", item.name))
        Text(text = String.format("Количество страниц: %d", item.page))
        Text(text = String.format("В наличии: %d", item.count))

        if (!isCartVisible) {
            BuyButton(onClick = { isCartVisible = true })
        } else {
            ShoppingCart(state)
        }
    }
}

@Composable
fun BuyButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("В корзину")
    }
}

@Composable
fun ShoppingCart(state: ListState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {


            Text(String.format("Корзина: %s", state.countBooksInCart), style = MaterialTheme.typography.headlineSmall)
        }
    }
}