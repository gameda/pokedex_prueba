package com.gameda.prueba_pokedex.domain.usecase

import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.repository.PokedexRepository
import javax.inject.Inject

class SetFavoritePokemonUseCase @Inject constructor(private val repository: PokedexRepository) {

    suspend operator fun invoke(id: PokemonId, isFavorite: Boolean){
        repository.setFavoritePokemon(id, isFavorite)
    }

}