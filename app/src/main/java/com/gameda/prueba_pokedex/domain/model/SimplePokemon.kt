package com.gameda.prueba_pokedex.domain.model

data class SimplePokemon(
    val id: PokemonId,
    val name: String,
    val urlPicture: String
)