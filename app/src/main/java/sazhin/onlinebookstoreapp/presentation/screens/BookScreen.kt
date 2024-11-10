package sazhin.onlinebookstoreapp.presentation.screens

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            AsyncImage(
//                model = item.photo,
//                modifier = Modifier
//                    .fillMaxWidth(0.5f),
//                contentDescription = null
//            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = item.name,
            )
            Text(
                text = item.page.toString(),
            )
        }
    }
}