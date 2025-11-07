package com.gameda.prueba_pokedex.data.local.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.PokemonType

@Entity(
    tableName = "detailed_pokemon_table",
    indices = [Index(value = ["id"], unique = true)]
)
data class DetailedPokemonEntity(
    @PrimaryKey val id: PokemonId,
    val name: String,
    val urlPicture: String,
    val types: List<PokemonType>,
    val weight: Double,
    val height: Double,
)

val DetailedPokemonEntity.asDetailedPokemon: DetailedPokemon
    get() = DetailedPokemon(
        id,
        name,
        urlPicture,
        types,
        weight,
        height
    )

val DetailedPokemon.asDetailedPokemonEntity: DetailedPokemonEntity
    get() = DetailedPokemonEntity(
        id,
        name,
        urlPicture,
        types,
        weight,
        height,
    )