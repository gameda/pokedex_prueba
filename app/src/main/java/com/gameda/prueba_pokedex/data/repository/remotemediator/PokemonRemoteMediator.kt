package com.gameda.prueba_pokedex.data.repository.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.gameda.prueba_pokedex.data.local.db.entity.SimplePokemonEntity
import com.gameda.prueba_pokedex.data.remote.model.asSimplePokemon
import com.gameda.prueba_pokedex.data.repository.contracts.LocalDataSource
import com.gameda.prueba_pokedex.data.repository.contracts.RemoteDataSoruce
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator @Inject constructor (
    private val remoteDataSource: RemoteDataSoruce,
    private val localDataSource: LocalDataSource): RemoteMediator<Int, SimplePokemonEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SimplePokemonEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH ->  // cuando se abre la app
                remoteDataSource.getPokemonsList(0, state.config.pageSize)

            LoadType.PREPEND -> // usuario scrolleó al final → cargar más.
                return MediatorResult.Success(endOfPaginationReached = true)

            LoadType.APPEND -> {
                val offset = state.lastItemOrNull()?.id ?: 0
                remoteDataSource.getPokemonsList(offset, state.config.pageSize)

            }
        }

        return page.fold(
            onSuccess = {
                localDataSource.savePokemons(
                    it.results.map { pokemon -> pokemon.asSimplePokemon },
                    loadType == LoadType.REFRESH)

                MediatorResult.Success(
                    endOfPaginationReached = it.next == null || it.results.isEmpty()
                )
            },
            onFailure = {
                MediatorResult.Error(it)
            }
        )
    }


}