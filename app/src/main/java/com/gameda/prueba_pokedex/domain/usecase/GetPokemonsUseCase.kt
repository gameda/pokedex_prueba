package com.gameda.prueba_pokedex.domain.usecase

import androidx.paging.PagingData
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val repository: PokedexRepository) {

    operator fun invoke():  Flow<PagingData<SimplePokemon>> =
         repository.getPokemonsList()

}