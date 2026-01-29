package com.gameda.prueba_pokedex.features.pokedex_home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gameda.prueba_pokedex.app.navigation.BottomRoutes
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.features.pokedex_home.screen.PokemonListScreen

fun NavController.navigateToHomePokedex() {
    navigate(BottomRoutes.Home)
}

fun NavGraphBuilder.pokemonListNavigationNode( navigateToPokemonDetails: (PokemonId) -> Unit) {

    composable<BottomRoutes.Home> {
        PokemonListScreen(
            hiltViewModel(),
            navigateToPokemonDetails
        )
    }
}

