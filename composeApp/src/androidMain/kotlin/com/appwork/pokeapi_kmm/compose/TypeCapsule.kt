package com.appwork.pokeapi_kmm.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.appwork.pokeapi_kmm.utils.AppColor
import com.appwork.pokeapi_kmm.utils.ColorType
import com.appwork.pokeapi_kmm.utils.toComposeColor

@Composable
fun TypeCapsule(
    text: String,
    systemImage: ImageVector? = null,
    font: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = Color.White,
    backgroundColor: ColorType = ColorType.Dark,
    paddingV: Dp = 6.dp,
    paddingH: Dp = 12.dp,
    cornerRadius: Dp = 16.dp,
    borderColor: Color? = null,
    borderWidth: Dp = 1.dp,
    shadow: Boolean = false,
    action: (() -> Unit)? = null
) {
    val shape = RoundedCornerShape(cornerRadius)

    val content: @Composable RowScope.() -> Unit = {
        if (systemImage != null) {
            Icon(
                imageVector = systemImage,
                contentDescription = null,
                tint = textColor
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            style = font,
            color = textColor,
            maxLines = 1
        )
    }

    if (action != null) {
        Button(
            onClick = action,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor.toComposeColor(),
                contentColor = textColor
            ),
            border = borderColor?.let { BorderStroke(borderWidth, it) },
            modifier = Modifier
                .defaultMinSize(minWidth = 0.dp, minHeight = 0.dp)
                .padding(horizontal = 0.dp, vertical = 0.dp)
                .then(
                    if (shadow) Modifier.shadow(4.dp, shape) else Modifier
                ),
            contentPadding = PaddingValues(horizontal = paddingH, vertical = paddingV)
        ) {
            content()
        }
    } else {
        Surface(
            color = backgroundColor.toComposeColor(),
            shape = shape,
            border = borderColor?.let { BorderStroke(borderWidth, it) },
            modifier = Modifier
                .padding(0.dp)
                .then(
                    if (shadow) Modifier.shadow(4.dp, shape) else Modifier
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = paddingH, vertical = paddingV),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
fun TypeCapsulePreview() {
    TypeCapsule(
        text = "fire",
    )
}
