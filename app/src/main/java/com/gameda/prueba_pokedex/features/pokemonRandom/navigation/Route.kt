package com.gameda.prueba_pokedex.features.pokemonRandom.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gameda.prueba_pokedex.app.navigation.PokedexRoutes
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.features.pokemonDetail.screen.PokemonDetailsScreen


fun NavController.navigateToPokemonRandom() {
    navigate(PokedexRoutes.Random)
}

fun NavGraphBuilder.pokemonRandomNavigationNode() {

    composable<PokedexRoutes.Random>{
        val pokemonId = (1..1000).random()

        PokemonDetailsScreen(
          pokemonId,
          pokemonDetailsViewModel = hiltViewModel()
      )
    }

}
