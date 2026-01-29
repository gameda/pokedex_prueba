package com.gameda.prueba_pokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gameda.prueba_pokedex.data.local.db.entity.asSimplePokemon
import com.gameda.prueba_pokedex.data.repository.contracts.LocalDataSource
import com.gameda.prueba_pokedex.data.repository.contracts.RemoteDataSoruce
import com.gameda.prueba_pokedex.data.repository.remotemediator.PokemonRemoteMediator
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class PokedexRepositoryImpl (private val localDataSource: LocalDataSource,
                             private val remoteDataSource: RemoteDataSoruce): PokedexRepository {

    override fun getPokemonsList(): Flow<PagingData<SimplePokemon>> {
        return Pager(
            PagingConfig(25),
            remoteMediator = PokemonRemoteMediator(remoteDataSource, localDataSource),
            pagingSourceFactory = { localDataSource.getPokemonsPaging() }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                //savePokemonDetail(entity.id)
                entity.asSimplePokemon } }

    }

    override fun getPokemonsFavorites(): Flow<List<SimplePokemon>> =
        localDataSource.getFavoritesPokemon()


    override suspend fun getPokemonDetails(pokemonId: PokemonId): Result<DetailedPokemon> {
        val local = localDataSource.getPokemonDetails(pokemonId)
        if (local.isSuccess) {
            return local
        } else {
            val remote = remoteDataSource.getPokemonDetails(pokemonId)
            if (remote.isSuccess) {
                localDataSource.setPokemonDetails(remote.getOrNull()!!)
                localDataSource.getPokemonDetails(pokemonId)
            }
            return remote
        }
    }



    override suspend fun setFavoritePokemon(pokemonId: PokemonId, isFavorite: Boolean) {
        localDataSource.setFavoritePokemon(pokemonId, isFavorite)
    }

    suspend fun savePokemonDetail(pokemonId: PokemonId){
        val remote = remoteDataSource.getPokemonDetails(pokemonId)
        if (remote.isSuccess) {
            localDataSource.setPokemonDetails(remote.getOrNull()!!)
        }
    }

}