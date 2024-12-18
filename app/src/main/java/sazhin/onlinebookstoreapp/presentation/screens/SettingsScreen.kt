package sazhin.onlinebookstoreapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import sazhin.onlinebookstoreapp.R
import sazhin.onlinebookstoreapp.viewModel.BookViewModel

@Parcelize
class SettingsScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val viewModel = koinViewModel<BookViewModel>()
            val state = viewModel.viewState

            var countBooks by remember { mutableIntStateOf(state.countBooks) }
            var inputText  by remember { mutableStateOf("") }

            Text(
                text = stringResource(R.string.count_books)
            )

            TextField(
                value = countBooks.toString(),
                onValueChange = { newText ->
                    inputText = newText
                    countBooks = newText.toIntOrNull() ?: 0
                },
                placeholder = {
                    Text("Введите количество книг")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            TextButton(
                onClick = {
                    viewModel.updateParameters(countBooks)
                }
            ) {
                Text(stringResource(R.string.confirm))
            }
        }
    }
}