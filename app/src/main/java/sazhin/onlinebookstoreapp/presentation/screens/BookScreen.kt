package sazhin.onlinebookstoreapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import sazhin.onlinebookstoreapp.domain.models.Book

@Parcelize
class BookScreen(
    private val item: Book,
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = item.path,
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = String.format("Название книги: %s", item.name))
            Text(text = String.format("Количество страниц: %d", item.page))
            Text(text = String.format("В наличии: %d", item.count))
            Text(text = String.format("Цена: %d руб.", item.price))
        }
    }
}