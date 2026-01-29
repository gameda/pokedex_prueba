package com.gameda.prueba_pokedex.features.pokemonRandom.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gameda.prueba_pokedex.app.navigation.BottomRoutes
import com.gameda.prueba_pokedex.app.navigation.DetailsScreen
import com.gameda.prueba_pokedex.features.pokemonDetail.screen.PokemonDetailsScreen


fun NavController.navigateToPokemonRandom() {
    navigate(DetailsScreen)
}

fun NavGraphBuilder.pokemonRandomNavigationNode() {

    composable<BottomRoutes.Random>{
        val pokemonId = (1..1000).random()

        PokemonDetailsScreen(
          pokemonId,
          pokemonDetailsViewModel = hiltViewModel()
      )
    }

}
