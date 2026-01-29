package com.gameda.prueba_pokedex.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.gameda.prueba_pokedex.data.local.db.dao.DetailedPokemonDao
import com.gameda.prueba_pokedex.data.local.db.dao.PokedexDao
import com.gameda.prueba_pokedex.data.local.db.entity.SimplePokemonEntity
import com.gameda.prueba_pokedex.data.local.db.entity.toDetailedPokemon
import com.gameda.prueba_pokedex.data.local.db.entity.toDetailedPokemonEntity
import com.gameda.prueba_pokedex.data.local.db.entity.asSimplePokemon
import com.gameda.prueba_pokedex.data.local.db.entity.asSimplePokemonEntity
import com.gameda.prueba_pokedex.data.repository.contracts.LocalDataSource
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.domain.model.toSimplePokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class LocalDataSourceImpl(val pokedexDao: PokedexDao,
                          val detailDao: DetailedPokemonDao): LocalDataSource {

    override fun getPokemonsPaging(): PagingSource<Int, SimplePokemonEntity> =
        pokedexDao.getPokemonsPaging()

    override fun getFavoritesPokemon(): Flow<List<SimplePokemon>> =
        detailDao.getFavoritiesPokemons().map { lista ->
            lista.map {
                it.toDetailedPokemon.toSimplePokemon }}

    override suspend fun setFavoritePokemon(pokemonId: PokemonId, isFavorite: Boolean) {
        detailDao.setPokemonFavorite(pokemonId, isFavorite)
    }

    override suspend fun savePokemons(pokemons: List<SimplePokemon>, clean: Boolean) {
        if(clean)
            pokedexDao.deleteAllPokemons()
        pokedexDao.insertPokemons(pokemons.map { it.asSimplePokemonEntity })
    }
    override suspend fun deleteAllPokemons() {
        pokedexDao.deleteAllPokemons()
    }

    override suspend fun getPokemonDetails(pokemonId: PokemonId): Result<DetailedPokemon> =
        runCatching {
            detailDao.getDetailedPokemon(pokemonId).toDetailedPokemon
        }




    override suspend fun setPokemonDetails(pokemon: DetailedPokemon) {
        detailDao.insertDetailedPokemon(pokemon.toDetailedPokemonEntity)
    }

}