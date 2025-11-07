package com.gameda.prueba_pokedex.data.remote.model

import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDTO>
)

data class PokemonDTO(
    val name: String,
    @SerializedName("url") val pokemonDetailsUrl: String
)

val PokemonDTO.asSimplePokemon: SimplePokemon
    get()  {
        val id = pokemonDetailsUrl.pokemonId
        return SimplePokemon(
            id,
            name,
            id.picUrl
        )
    }

private val String.pokemonId: PokemonId
    get() = this.substringAfter("pokemon").replace("/", "").toInt()

private val PokemonId.picUrl: String
    get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${this}.png"