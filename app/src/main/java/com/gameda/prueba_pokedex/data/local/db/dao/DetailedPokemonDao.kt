package com.gameda.prueba_pokedex.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gameda.prueba_pokedex.data.local.db.entity.DetailedPokemonEntity
import com.gameda.prueba_pokedex.data.local.db.entity.SimplePokemonEntity
import com.gameda.prueba_pokedex.domain.model.PokemonId
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailedPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailedPokemon(pokemon: DetailedPokemonEntity)

    @Query("SELECT * FROM detailed_pokemon_table WHERE id = :pokemonId")
    suspend fun getDetailedPokemon(pokemonId: PokemonId): DetailedPokemonEntity

    @Query("UPDATE detailed_pokemon_table SET favority = :isFavorite WHERE id = :pokemonId")
    suspend fun setPokemonFavorite(pokemonId: PokemonId, isFavorite: Boolean)

    @Query("SELECT * FROM detailed_pokemon_table WHERE favority == 1 ORDER BY id ASC")
    fun getFavoritiesPokemons(): Flow<List<DetailedPokemonEntity>>
    @Delete
    suspend fun deleteDetailedPokemon(pokemon: DetailedPokemonEntity)
}