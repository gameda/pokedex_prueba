package com.gameda.prueba_pokedex.data.remote.model

import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.PokemonType
import com.google.gson.annotations.SerializedName

data class PokemonDetailsDTO(
    val name: String,
    val types: List<TypesDTO>,
    val height: Int,
    val weight: Int
)

data class StatsDTO(
    @SerializedName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: StatDTO
)

data class StatDTO(
    val name: String,
    @SerializedName("url") val statDetailsUrl: String
)

data class TypesDTO(
    val slot: Int,
    val type: TypeDTO
)

data class TypeDTO(
    val name: String,
    val url: String
)

fun PokemonDetailsDTO.asDetailedPokemon(id: PokemonId) =
    DetailedPokemon(
        id,
        name,
        urlPicture = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
        types.asPokemonTypes,
        weight.div(10.0),
        height.div(10.0)
    )

val List<TypesDTO>.asPokemonTypes: List<PokemonType>
    get() = map { PokemonType(it.type.name) }