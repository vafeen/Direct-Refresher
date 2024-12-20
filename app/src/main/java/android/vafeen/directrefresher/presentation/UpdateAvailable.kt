package android.vafeen.directrefresher.presentation

import android.vafeen.directrefresher.ui.theme.FontSize
import android.vafeen.directrefresher.ui.theme.updateAvailableColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun UpdateAvailable(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(updateAvailableColor)
            .clickable(onClick = onClick)
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
    ) {
        Text(
            text = "Update",
            color = Color.White,
            fontSize = FontSize.small17
        )
    }
}