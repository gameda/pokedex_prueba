package com.gameda.prueba_pokedex.features.pokedex_home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.domain.usecase.GetPokemonsUseCase
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel

class PokemonsViewModel  @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase): ViewModel(){

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()




    val pokemonList: Flow<PagingData<SimplePokemon>> =
        getPokemonsUseCase.invoke().cachedIn(viewModelScope)    //Guarda en memoria temporal los datos para que no se recarguen cada vez que rotas la pantalla.

}