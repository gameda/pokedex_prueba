package com.gameda.prueba_pokedex.features.pokemonDetail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gameda.prueba_pokedex.app.navigation.PokedexRoutes
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.features.pokemonDetail.screen.PokemonDetailsScreen


fun NavController.navigateToPokemonDetails(pokemonId: PokemonId) {
    navigate(PokedexRoutes.Details(pokemonId))
}

fun NavGraphBuilder.pokemonDetailsNavigationNode() {

    composable<PokedexRoutes.Details>{
        val args = it.toRoute<PokedexRoutes.Details>()
        val pokemonId = args.id

        PokemonDetailsScreen(
          pokemonId,
          pokemonDetailsViewModel = hiltViewModel()
      )
    }

}
