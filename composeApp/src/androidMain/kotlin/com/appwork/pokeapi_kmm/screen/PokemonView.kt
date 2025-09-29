package com.appwork.pokeapi_kmm.screen

import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.appwork.pokeapi_kmm.R
import com.appwork.pokeapi_kmm.utils.ColorType
import com.appwork.pokeapi_kmm.utils.FontUtils
import com.appwork.pokeapi_kmm.utils.toComposeColor
import com.appwork.pokeapi_kmm.view_model.PokemonViewModel
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import com.appwork.pokeapi_kmm.compose.PokemonStats
import com.appwork.pokeapi_kmm.compose.TypeCapsule
import com.appwork.pokeapi_kmm.data.SharedImageLoader
import com.appwork.pokeapi_kmm.services.KtorClientFactory
import com.appwork.pokeapi_kmm.utils.AppColor
import com.appwork.pokeapi_kmm.utils.toKg
import com.appwork.pokeapi_kmm.utils.toMeters
import kotlinx.coroutines.launch

@Composable
fun PokemonView(
    viewModel: PokemonViewModel,
    onBack: () -> Unit
) {

    val current by viewModel.current.collectAsState()
    val pokemon by viewModel.pokemon.collectAsState()
    val species by viewModel.pokemonSpecies.collectAsState()
    val imageBytes by viewModel.imageBytes.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                ColorType.fromString(pokemon?.types?.firstOrNull()?.type?.name ?: "").toComposeColor()
            )
            .systemBarsPadding(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackgroundView(viewModel = viewModel)

        HeaderView(
            viewModel = viewModel,
            onBack = onBack
        )
    }
}

@Composable
private fun HeaderView(
    viewModel: PokemonViewModel,
    onBack: () -> Unit
) {

    val current by viewModel.current.collectAsState()
    val pokemon by viewModel.pokemon.collectAsState()
    val species by viewModel.pokemonSpecies.collectAsState()
    val imageBytes by viewModel.imageBytes.collectAsState()

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(76.dp)
                .padding(horizontal = 20.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = pokemon?.name?.replaceFirstChar { it.uppercase() } ?: "",
                style = FontUtils.headerHeadline.textStyle,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.tag),
                    contentDescription = "ID",
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    text = String.format("%03d", pokemon?.id ?: 0),
                    style = FontUtils.caption.textStyle,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        BannerView(viewModel = viewModel)
    }
}

@Composable
private fun BannerView(viewModel: PokemonViewModel) {

    val current by viewModel.current.collectAsState()
    val pokemon by viewModel.pokemon.collectAsState()
    val species by viewModel.pokemonSpecies.collectAsState()
    val imageBytes by viewModel.imageBytes.collectAsState()
    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    val scope = rememberCoroutineScope()
    val loader = remember { SharedImageLoader(KtorClientFactory().create()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(200.dp)
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { viewModel.movePrevious() }) {
            Icon(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = "Prev",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        if (imageBytes != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes!!.size)
            val imageBitmap = bitmap.asImageBitmap()

            Image(
                painter = BitmapPainter(imageBitmap),
                contentDescription = viewModel.pokemon.value?.name,
                modifier = Modifier.size(200.dp)
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        IconButton(onClick = { viewModel.moveNext() }) {
            Icon(
                painter = painterResource(id = R.drawable.chevron_right),
                contentDescription = "Next",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun BackgroundView(viewModel: PokemonViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.1f),
                modifier = Modifier.size(208.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            ContentView(viewModel = viewModel)
        }
    }
}

@Composable
private fun ContentView(viewModel: PokemonViewModel) {
    val current by viewModel.current.collectAsState()
    val pokemon by viewModel.pokemon.collectAsState()
    val species by viewModel.pokemonSpecies.collectAsState()
    val imageBytes by viewModel.imageBytes.collectAsState()
    LazyColumn(
        modifier = Modifier
            .padding(top = 72.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Types
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pokemon?.types?.forEach { type ->
                    TypeCapsule(
                        text = type.type.name.replaceFirstChar { it.uppercase() },
                        backgroundColor = ColorType.fromString(type.type.name)
                    )
                }
            }
            Spacer(Modifier.height(16.dp))

            // About
            Text(
                text = "About",
                style = FontUtils.headerSubtitle1.textStyle,
                color = ColorType.fromString(pokemon?.types?.firstOrNull()?.type?.name ?: "")
                    .toComposeColor(),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Weight / Height / Moves grid
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .height(56.dp)
                    .padding(bottom = 16.dp)

            ) {
                InfoColumn(
                    iconRes = R.drawable.weight,
                    label = "Weight",
                    value = ((pokemon?.weight ?: 0).toKg())
                )
                DividerLine()
                InfoColumn(
                    iconRes = R.drawable.straighten,
                    label = "Height",
                    value = ((pokemon?.height ?: 0).toMeters())
                )
                DividerLine()
                InfoColumn(
                    label = "Moves",
                    values = pokemon?.abilities
                        ?.map { it.ability?.name?.replaceFirstChar { c -> c.uppercase() } ?: "" }
                )
            }

            // Flavor text
            Text(
                text = (species?.flavorTextEntries?.firstOrNull()?.flavor_text ?: "")
                    .replace("\n", " "),
                style = FontUtils.body3.textStyle,
                color = AppColor.GrayscaleDark.toComposeColor(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            )

            // Base Stats
            Text(
                text = "Base Stat",
                style = FontUtils.headerSubtitle1.textStyle,
                color = ColorType.fromString(pokemon?.types?.firstOrNull()?.type?.name ?: "")
                    .toComposeColor(),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                pokemon?.stats?.forEach { stat ->
                    PokemonStats(
                        title = stat.stat.displayName(),
                        stat = stat.baseStat.toInt(),
                        typeColor = ColorType.fromString(
                            pokemon?.types?.firstOrNull()?.type?.name ?: ""
                        ).toComposeColor()
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoColumn(
    @DrawableRes iconRes: Int? = null,
    label: String,
    value: String? = null,
    values: List<String>? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(104.dp)) {
        if (iconRes != null && value != null) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = label,
                    tint = AppColor.GrayscaleDark.toComposeColor(),
                    modifier = Modifier.size(16.dp).padding(end = 4.dp)
                )
                Text(value, style = FontUtils.body3.textStyle, color = AppColor.GrayscaleDark.toComposeColor())
            }
        } else if (values != null) {
            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                values.forEach {
                    Text(it, style = FontUtils.body3.textStyle, color = AppColor.GrayscaleDark.toComposeColor())
                }
            }
        }
        Text(label, style = FontUtils.caption.textStyle, color = AppColor.GrayscaleMedium.toComposeColor())
    }
}

@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .width(2.dp)
            .fillMaxHeight()
            .background(AppColor.GrayscaleLight.toComposeColor())
    )
}
