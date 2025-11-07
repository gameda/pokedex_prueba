@file:OptIn(ExperimentalPagingApi::class)

package com.gameda.prueba_pokedex.data.repository.contracts

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import com.gameda.prueba_pokedex.data.remote.model.PokemonListResponse
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow

interface RemoteDataSoruce {

    suspend fun getPokemonsList(offset: Int = 0, limit: Int = 10000): Result<PokemonListResponse>

    suspend fun getPokemonDetails(pokemonId: PokemonId): Result<DetailedPokemon>

}