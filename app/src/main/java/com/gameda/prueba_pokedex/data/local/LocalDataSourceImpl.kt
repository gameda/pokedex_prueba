package com.gameda.prueba_pokedex.data.local

import android.content.res.Resources.NotFoundException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.gameda.prueba_pokedex.data.local.db.dao.DetailedPokemonDao
import com.gameda.prueba_pokedex.data.local.db.dao.PokedexDao
import com.gameda.prueba_pokedex.data.local.db.entity.SimplePokemonEntity
import com.gameda.prueba_pokedex.data.local.db.entity.asDetailedPokemon
import com.gameda.prueba_pokedex.data.local.db.entity.asDetailedPokemonEntity
import com.gameda.prueba_pokedex.data.local.db.entity.asSimplePokemonEntity
import com.gameda.prueba_pokedex.data.repository.contracts.LocalDataSource
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon

@OptIn(ExperimentalPagingApi::class)
class LocalDataSourceImpl(val pokedexDao: PokedexDao,
                          val detailDao: DetailedPokemonDao): LocalDataSource{

    override fun getPokemonsPaging(): PagingSource<Int, SimplePokemonEntity> =
        pokedexDao.getPokemonsPaging()

    override suspend fun savePokemons(pokemons: List<SimplePokemon>, clean: Boolean) {
        if(clean)
            pokedexDao.deleteAllPokemons()
        pokedexDao.insertPokemons(pokemons.map { it.asSimplePokemonEntity })
    }
    override suspend fun deleteAllPokemons() {
        pokedexDao.deleteAllPokemons()
    }

    override suspend fun getPokemonDetails(pokemonId: PokemonId): Result<DetailedPokemon> =
        detailDao.getDetailedPokemon(pokemonId).let {
            Result.success(it?.asDetailedPokemon ?: throw NotFoundException())
        }


    override suspend fun setPokemonDetails(pokemon: DetailedPokemon) {
        detailDao.insertDetailedPokemon(pokemon.asDetailedPokemonEntity)
    }

}