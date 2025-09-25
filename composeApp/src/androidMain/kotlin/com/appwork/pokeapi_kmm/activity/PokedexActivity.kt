package com.appwork.pokeapi_kmm.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.appwork.pokeapi_kmm.screen.PokedexScreen

class PokedexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            PokedexScreen()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {

}