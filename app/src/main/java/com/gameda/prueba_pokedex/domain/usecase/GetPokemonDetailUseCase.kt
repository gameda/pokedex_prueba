package com.gameda.prueba_pokedex.domain.usecase

import androidx.paging.PagingData
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(private val repository: PokedexRepository) {

    suspend operator fun invoke(id: PokemonId): Result<DetailedPokemon> =
         repository.getPokemonDetails(id)

}