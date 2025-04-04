package com.example.wordapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.wordapp.data.room.Word

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordDisplay(
    word: Word?,
    displayOption: String,
    onLongPress: (Word) -> Unit = {},
    modifier: Modifier = Modifier
) {
    word?.let { currentWord ->
        val configuration = LocalConfiguration.current
        val orientation = configuration.orientation
        val bothVisible = displayOption != "Монгол үгийг харуулах" &&
                displayOption != "Зөвхөн гадаад үгийг харуулах"

        val mainModifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onLongClick = { onLongPress(currentWord) }
            )
            .padding(
                horizontal = 16.dp,
                vertical = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 16.dp else 16.dp
            )

        if (orientation == Configuration.ORIENTATION_LANDSCAPE && bothVisible) {
            Row(
                modifier = mainModifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (displayOption != "Монгол үгийг харуулах") {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(30.dp)
                    ) {
                        Text(
                            text = currentWord.foreignWord,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                if (displayOption != "Зөвхөн гадаад үгийг харуулах") {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(30.dp)
                    ) {
                        Text(
                            text = currentWord.mongolianWord,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            Column(modifier = mainModifier) {
                if (displayOption != "Монгол үгийг харуулах") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(30.dp)
                    ) {
                        Text(
                            text = currentWord.foreignWord,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                if (displayOption != "Зөвхөн гадаад үгийг харуулах") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(30.dp)
                    ) {
                        Text(
                            text = currentWord.mongolianWord,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}