package com.gameda.prueba_pokedex.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gameda.prueba_pokedex.data.local.db.entity.DetailedPokemonEntity
import com.gameda.prueba_pokedex.data.local.db.entity.SimplePokemonEntity
import com.gameda.prueba_pokedex.domain.model.PokemonId

@Dao
interface PokedexDao {
    @Query("SELECT * FROM simple_pokemon_table ORDER BY id ASC")
    fun getPokemonsPaging(): PagingSource<Int, SimplePokemonEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPokemons(pokemons: List<SimplePokemonEntity>)

    @Query("SELECT * FROM detailed_pokemon_table WHERE id = :pokemonId")
    suspend fun getDetailedPokemon(pokemonId: PokemonId): DetailedPokemonEntity?

    @Query("DELETE FROM simple_pokemon_table")
    suspend fun deleteAllPokemons()
}