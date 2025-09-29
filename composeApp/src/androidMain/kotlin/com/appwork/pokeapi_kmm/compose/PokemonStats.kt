package com.appwork.pokeapi_kmm.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.appwork.pokeapi_kmm.utils.AppColor
import com.appwork.pokeapi_kmm.utils.FontUtils
import com.appwork.pokeapi_kmm.utils.toComposeColor

@Composable
fun PokemonStats(
    title: String,
    stat: Int,
    typeColor: Color,
    titleFont: TextStyle = FontUtils.headerSubtitle1.textStyle,
    statFont: TextStyle = MaterialTheme.typography.bodySmall,
    dividerColor: Color = Color.LightGray,
    maxStat: Int = 100, // so bar is relative to this value
    barHeight: Dp = 4.dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Title
        Text(
            text = title,
            style = titleFont,
            color = typeColor,
            modifier = Modifier.width(35.dp),
            maxLines = 1,
            textAlign = TextAlign.End
        )

        // Divider rectangle
        Box(
            modifier = Modifier
                .padding(start = 4.dp)
                .width(2.dp)
                .height(16.dp)
                .background(dividerColor)
        )

        // Stat number
        Text(
            text = stat.toString(),
            style = statFont,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(start = 4.dp)
                .width(20.dp),
            maxLines = 1,
            textAlign = TextAlign.End
        )

        // Progress bar
        Box(
            modifier = Modifier
                .padding(start = 4.dp)
                .height(16.dp)
                .weight(1f), // expand to fill remaining space
            contentAlignment = Alignment.CenterStart
        ) {
            // Background bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(barHeight)
                    .background(typeColor.copy(alpha = 0.2f))
            )

            // Foreground bar (proportional to stat)
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = (stat.toFloat() / maxStat.toFloat()).coerceIn(0f, 1f))
                    .height(barHeight)
                    .background(typeColor)
            )
        }
    }
}

@Preview
@Composable
fun PokemonStasPreview() {
    PokemonStats(
        title = "HP",
        stat = 50,
        typeColor = AppColor.Wireframe.toComposeColor(),
    )
}
