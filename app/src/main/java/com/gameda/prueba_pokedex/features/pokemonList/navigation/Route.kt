package com.gameda.prueba_pokedex.features.pokemonList.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gameda.prueba_pokedex.app.navigation.PokedexRoutes
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.features.pokemonList.screen.PokemonListScreen

fun NavController.navigateToPokemonList() {
    navigate(PokedexRoutes.Lista)
}

fun NavGraphBuilder.pokemonListNavigationNode(navigateToPokemonDetails: (PokemonId) -> Unit) {
    composable<PokedexRoutes.Lista> {
        PokemonListScreen(
            hiltViewModel(),
            navigateToPokemonDetails
        )
    }
}

