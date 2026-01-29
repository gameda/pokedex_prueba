package com.gameda.prueba_pokedex.features.pokemonFavoriteList.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.domain.usecase.GetFavoritePokemonsUseCase
import com.gameda.prueba_pokedex.domain.usecase.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class PokemonsFavViewModel  @Inject constructor(
    private val getFavoritePokemonsUseCase: GetFavoritePokemonsUseCase ): ViewModel(){

    private var _pokemonList = mutableStateListOf<SimplePokemon>()
    val pokemonList: List<SimplePokemon> = _pokemonList


    init {
        getFavoritesPokemons()
    }

    fun getFavoritesPokemons() = viewModelScope.launch {
        getFavoritePokemonsUseCase().collect {
            _pokemonList.addAll(it)

        }
    }
}