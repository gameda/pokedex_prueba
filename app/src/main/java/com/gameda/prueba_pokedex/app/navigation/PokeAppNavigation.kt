package com.gameda.prueba_pokedex.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gameda.prueba_pokedex.features.common.composables.PokeAppScaffold
import com.gameda.prueba_pokedex.features.pokemonDetail.navigation.navigateToPokemonDetails
import com.gameda.prueba_pokedex.features.pokemonDetail.navigation.pokemonDetailsNavigationNode
import com.gameda.prueba_pokedex.features.pokemonList.navigation.pokemonListNavigationNode

@Composable
fun PokeAppNavigation(

    //pokemonDetailsViewModel: @Composable() () -> PokemonDetailsViewModel
) {
    val navController = rememberNavController()

    PokeAppScaffold(navController = navController) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = PokedexRoutes.Lista
        ) {
            pokemonListNavigationNode(navController::navigateToPokemonDetails)
            pokemonDetailsNavigationNode()

        }
    }
}