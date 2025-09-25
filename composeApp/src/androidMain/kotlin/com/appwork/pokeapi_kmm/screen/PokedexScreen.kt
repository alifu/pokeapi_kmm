package com.appwork.pokeapi_kmm.screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import com.appwork.pokeapi_kmm.utils.toComposeColor
import com.appwork.pokeapi_kmm.utils.AppColor
import com.appwork.pokeapi_kmm.utils.FontUtils
import com.appwork.pokeapi_kmm.R
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appwork.pokeapi_kmm.view_model.PokedexViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import com.appwork.pokeapi_kmm.compose.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

@Composable
fun PokedexScreen(viewModel: PokedexViewModel = viewModel()) {
    val pokedexResponse by viewModel.list.observeAsState()
    var buttonFrame by remember { mutableStateOf<Rect?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadPage()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.Primary.toComposeColor()) // background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .systemBarsPadding()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "pokeball",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )

                Text(
                    text = "Pokedex",
                    color = Color.White,
                    style = FontUtils.headerHeadline.textStyle
                )

                Spacer(modifier = Modifier.weight(1f))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBox(
                    text = viewModel.searchText,
                    onTextChange = { viewModel.updateSearchText(it) },
                    onCommit = { /* handle IME Done if needed */ },
                    modifier = Modifier
                        .weight(1f)
                        .width(100.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                SortButton(
                    isSorted = false,
                    onSortedChange = { /* handle toggle if needed */ },
                    onPositionChange = { rect -> buttonFrame = rect },
                    action = { viewModel.toggleSortMenu() },
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }
    }
}
