package com.gameda.prueba_pokedex.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gameda.prueba_pokedex.data.local.db.entity.DetailedPokemonEntity
import com.gameda.prueba_pokedex.domain.model.PokemonId

@Dao
interface DetailedPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailedPokemon(pokemon: DetailedPokemonEntity)

    @Query("SELECT * FROM detailed_pokemon_table WHERE id = :pokemonId")
    suspend fun getDetailedPokemon(pokemonId: PokemonId): DetailedPokemonEntity?

    @Delete
    suspend fun deleteDetailedPokemon(pokemon: DetailedPokemonEntity)
}