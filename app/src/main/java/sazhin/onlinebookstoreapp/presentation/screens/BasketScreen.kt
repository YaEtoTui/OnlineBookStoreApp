package sazhin.onlinebookstoreapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import sazhin.onlinebookstoreapp.R
import sazhin.onlinebookstoreapp.domain.models.Book
import sazhin.onlinebookstoreapp.viewModel.BookInCartViewModel
import sazhin.onlinebookstoreapp.viewModel.BookViewModel

@Parcelize
class BasketScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<BookInCartViewModel>()
        val state = viewModel.viewState

        viewModel.loadBooksInCart()

        Box(modifier = Modifier.fillMaxSize()) {
            Column(Modifier.fillMaxSize()) {

                val lazyGridState = rememberLazyGridState()

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    state = lazyGridState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.items) {
                        ConstructorItem(item = it, navigation)
                    }
                }
            }

            Text(text = String.format("Сумма к оплате: %d руб.", viewModel.checkSumForAllBook()))

            Button(
                onClick = { /* Handle buy button click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(text = stringResource(R.string.buy))
            }
        }
    }
}

@Composable
private fun ConstructorItem(
    item: Book,
    navigation: StackNavContainer? = null,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigation?.forward(BookScreen(item)) }
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = item.path,
            modifier = Modifier
                .size(256.dp),
            contentDescription = null,
        )
        Text(
            text = String.format("Название книги: %s", item.name),
            fontSize = 10.sp,
        )
    }
}