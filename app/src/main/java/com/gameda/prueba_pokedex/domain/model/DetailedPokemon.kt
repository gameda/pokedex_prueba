package com.gameda.prueba_pokedex.domain.model

data class DetailedPokemon(
    val id: PokemonId,
    val name: String,
    val urlPicture: String,
    val types: List<PokemonType>,
    val weight: Double, // in kgs
    val height: Double // in mts
)


data class PokemonType(
    val name: String
)