package com.gameda.prueba_pokedex.domain.repository

import androidx.paging.PagingData
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    fun getPokemonsList(): Flow<PagingData<SimplePokemon>>

    suspend fun getPokemonDetails(pokemonId: PokemonId): Result<DetailedPokemon>
}