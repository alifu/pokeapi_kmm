package com.appwork.pokeapi_kmm.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appwork.pokeapi_kmm.models.Pokedex
import com.appwork.pokeapi_kmm.view_model.PokemonViewModel

@Composable
fun MyApp() {
    val navController = rememberNavController()

    var pokedexData: List<Pokedex> = emptyList()

    NavHost(
        navController = navController,
        startDestination = "pokedexView"
    ) {
        composable("pokedexView") {
            PokedexView(
                onNavigateToDetail = { pokedex, id ->
                    pokedexData = pokedex
                    navController.navigate("pokemonView/$id")
                }
            )
        }

        composable(
            route = "pokemonView/{pokemonId}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
            PokemonView(
                viewModel = PokemonViewModel(results = pokedexData, startIndex = pokemonId),
                onBack = { navController.popBackStack() }
            )
        }
    }
}