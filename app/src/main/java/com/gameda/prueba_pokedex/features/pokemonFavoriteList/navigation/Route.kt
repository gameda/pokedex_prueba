package com.gameda.prueba_pokedex.features.pokemonFavoriteList.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gameda.prueba_pokedex.app.navigation.BottomRoutes
import com.gameda.prueba_pokedex.features.pokemonFavoriteList.screen.PokemonFavoriteScreen


fun NavGraphBuilder.pokemonFavoriteNavigationNode() {

        composable<BottomRoutes.Favorites> {

            PokemonFavoriteScreen(
                hiltViewModel(),
                {  }
            )
        }
}
