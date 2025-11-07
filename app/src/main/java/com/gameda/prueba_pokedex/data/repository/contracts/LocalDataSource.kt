package com.gameda.prueba_pokedex.data.repository.contracts

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.gameda.prueba_pokedex.data.local.db.entity.SimplePokemonEntity
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon

@OptIn(ExperimentalPagingApi::class)
interface LocalDataSource {

    fun getPokemonsPaging(): PagingSource<Int, SimplePokemonEntity>

    suspend fun savePokemons(pokemons: List<SimplePokemon>, clean: Boolean)

    suspend fun getPokemonDetails(pokemonId: PokemonId): Result<DetailedPokemon>

    suspend fun setPokemonDetails(pokemon: DetailedPokemon)

    suspend fun deleteAllPokemons()
}