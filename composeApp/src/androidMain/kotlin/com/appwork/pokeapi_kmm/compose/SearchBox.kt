package com.appwork.pokeapi_kmm.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.appwork.pokeapi_kmm.R
import com.appwork.pokeapi_kmm.utils.AppColor
import com.appwork.pokeapi_kmm.utils.FontUtils
import com.appwork.pokeapi_kmm.utils.toComposeColor

@Composable
fun SearchBox(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String = "Search...",
    onCommit: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .padding(4.dp)
            .shadow(
                elevation = if (text.isEmpty()) 2.dp else 0.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search icon
        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search",
            tint = AppColor.Primary.toComposeColor(),
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Text field
        Box(modifier = Modifier.weight(1f)) {
            if (text.isEmpty()) {
                Text(
                    text = placeholder,
                    style = TextStyle(color = Color.Gray)
                )
            }
            BasicTextField(
                value = text,
                onValueChange = { onTextChange(it) },
                textStyle = FontUtils.body3.textStyle,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onCommit?.invoke()
                    }
                )
            )
        }

        // Clear button
        if (text.isNotEmpty()) {
            IconButton(onClick = { onTextChange("") }) {
                Icon(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "Clear",
                    tint = AppColor.Primary.toComposeColor(),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
