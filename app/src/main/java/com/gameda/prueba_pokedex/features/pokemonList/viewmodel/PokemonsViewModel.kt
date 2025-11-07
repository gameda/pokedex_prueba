package com.gameda.prueba_pokedex.features.pokemonList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gameda.prueba_pokedex.data.remote.model.PokemonDTO
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.domain.usecase.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel

class PokemonsViewModel  @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase): ViewModel(){

    val pokemonList: Flow<PagingData<SimplePokemon>> =
        getPokemonsUseCase.invoke().cachedIn(viewModelScope)
}