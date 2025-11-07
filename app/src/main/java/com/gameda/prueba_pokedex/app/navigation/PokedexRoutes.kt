package com.gameda.prueba_pokedex.app.navigation

import com.gameda.prueba_pokedex.domain.model.PokemonId
import kotlinx.serialization.Serializable

@Serializable
sealed class PokedexRoutes(
    val title: String,
) {
    @Serializable
    data object Lista: PokedexRoutes("List")

    @Serializable
    data object Random: PokedexRoutes("Random" )

    @Serializable
    data object Favorities: PokedexRoutes("Favorties")

    @Serializable
    data class Details(val id: PokemonId): PokedexRoutes("Details")

}

val bottomNavItems = listOf(
    PokedexRoutes.Lista,
    PokedexRoutes.Random,
    PokedexRoutes.Favorities,
)
