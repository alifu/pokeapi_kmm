package com.appwork.pokeapi_kmm.compose

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appwork.pokeapi_kmm.R
import com.appwork.pokeapi_kmm.data.SharedImageLoader
import com.appwork.pokeapi_kmm.services.KtorClientFactory
import com.appwork.pokeapi_kmm.utils.AppColor
import com.appwork.pokeapi_kmm.utils.FontUtils
import com.appwork.pokeapi_kmm.utils.toComposeColor
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PokemonCard(
    name: String,
    imageURL: String?,
    idTag: String,
    modifier: Modifier = Modifier
) {
    // State for image bitmap
    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    val scope = rememberCoroutineScope()
    val loader = remember { SharedImageLoader(KtorClientFactory().create()) }

    Box(
        modifier = modifier
            .height(108.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(
                border = BorderStroke(1.dp, AppColor.Background.toComposeColor()), // Define border width and color
                shape = RoundedCornerShape(8.dp) // Define border shape (e.g., rounded corners)
            )
            .padding(0.dp)
    ) {
        // Bottom info bar
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxWidth()
                    .background(AppColor.Background.toComposeColor()),
            ) {
                Text(
                    text = name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(start = 8.dp, top = 16.dp, bottom = 4.dp),
                    textAlign = TextAlign.Center,
                    style = FontUtils.body3.textStyle,
                    color = AppColor.GrayscaleDark.toComposeColor()
                )
            }
        }

        // Pokemon image
        if (bitmap != null) {
            Image(
                bitmap = bitmap!!.asImageBitmap(),
                contentDescription = name,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center)
            )

            // Load image from URL via SharedImageLoader
            LaunchedEffect(imageURL) {
                imageURL?.let { url ->
                    scope.launch {
                        loader.load(url)?.let { byteArray ->
                            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                            bitmap = bmp
                        }
                    }
                }
            }
        }

        // Top-right tag
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 6.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tag),
                contentDescription = "tag",
                tint = AppColor.GrayscaleMedium.toComposeColor(),
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = idTag.padStart(3, '0'),
                fontSize = 12.sp,
                color = AppColor.GrayscaleMedium.toComposeColor()
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    PokemonCard(
        name = "Bulbasaur",
        imageURL = "",
        idTag = "0"
    )
}