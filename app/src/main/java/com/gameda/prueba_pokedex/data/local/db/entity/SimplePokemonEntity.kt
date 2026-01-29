package com.gameda.prueba_pokedex.data.local.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon

@Entity(
    tableName = "simple_pokemon_table",
    indices = [Index(value = ["id"], unique = true)]
)
data class SimplePokemonEntity(
    @PrimaryKey val id: PokemonId,
    val name: String,
    val urlPicture: String,
    val favority: Boolean = false
)
val SimplePokemonEntity.asSimplePokemon: SimplePokemon
    get() = SimplePokemon(id, name, urlPicture)

val SimplePokemon.asSimplePokemonEntity: SimplePokemonEntity
    get() = SimplePokemonEntity(id, name, urlPicture)