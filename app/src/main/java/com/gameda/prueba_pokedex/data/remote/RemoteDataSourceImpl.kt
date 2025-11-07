package com.gameda.prueba_pokedex.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import com.gameda.prueba_pokedex.data.remote.api.PokedexApiService
import com.gameda.prueba_pokedex.data.remote.model.PokemonListResponse
import com.gameda.prueba_pokedex.data.remote.model.asDetailedPokemon
import com.gameda.prueba_pokedex.data.repository.contracts.RemoteDataSoruce
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.utils.serviceHandlerExceptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(private val pokedexApi: PokedexApiService): RemoteDataSoruce {

   override suspend fun getPokemonsList(offset: Int, limit: Int): Result<PokemonListResponse> =
       serviceHandlerExceptions {
           pokedexApi.getPokemonsList(offset, limit)
       }

    override suspend fun getPokemonDetails(pokemonId: PokemonId): Result<DetailedPokemon> =
        serviceHandlerExceptions {
            pokedexApi.getPokemonDetails(pokemonId).asDetailedPokemon(pokemonId)

        }
}