package com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.usecase.GetPokemonDetailUseCase
import com.gameda.prueba_pokedex.domain.usecase.SetFavoritePokemonUseCase
import com.gameda.prueba_pokedex.features.common.utils.enums.ScreenStatus.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailUseCase,
    private val setFavoriteUseCase: SetFavoritePokemonUseCase
): ViewModel() {

    var uiScreen by mutableStateOf(SUCCESS)

    private  val _uiState = MutableStateFlow(UiState())
    var uiState: StateFlow<UiState> = _uiState

    private val favPokemonState by mutableStateOf(false)

    fun getPokemonDetails(id: PokemonId) {
        if(_uiState.value.pokemonDetail == null) {

            viewModelScope.launch {
                uiScreen = LOADING

                val pokemonDetails = getPokemonDetailsUseCase.invoke(id)

                 if (pokemonDetails.isSuccess) {
                    uiScreen = SUCCESS
                    _uiState.update { it.copy(
                            pokemonDetail = pokemonDetails.getOrNull()
                    ) }
                } else {
                    uiScreen = ERROR
                }
            }
        }
    }

    fun setMovieFavoriteOrNot(id: PokemonId, isFavorite: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            setFavoriteUseCase(id, isFavorite)
            //uiState.isFavorite = !movieSelected.isFavoritie
    }

    fun onEvent(event: UiEvent){
        when(event){
            is UiEvent.OnFavoritePressed -> {
                setMovieFavoriteOrNot(event.pokemonId, event.isFavorite)
            }
            is UiEvent.OnSearchPokemon -> {
                val name = event.text
            }
            else -> {}
        }
    }

}


data class UiState(
    var pokemonDetail: DetailedPokemon? = null,
    //var uiScreen: ScreenStatus = LOADING
)
sealed interface UiEvent{
    class OnFavoritePressed(val pokemonId: PokemonId, val isFavorite: Boolean): UiEvent
    class OnSearchPokemon(val text: String): UiEvent

}