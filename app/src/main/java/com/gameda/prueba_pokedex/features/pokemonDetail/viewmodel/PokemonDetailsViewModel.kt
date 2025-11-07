package com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gameda.prueba_pokedex.domain.model.DetailedPokemon
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailUseCase,
): ViewModel() {

    private val _uiState = MutableLiveData<PokemonDetailsUiState>()
    val uiState: LiveData<PokemonDetailsUiState> = _uiState

    fun getPokemonDetails(id: PokemonId) {
        if(_uiState.value == null) {
            viewModelScope.launch {
                _uiState.value = PokemonDetailsLoadingUiState

                val pokemonDetails = getPokemonDetailsUseCase.invoke(id)
                _uiState.value = if (pokemonDetails.isSuccess) {
                    PokemonDetailsLoadingSuccessUiState(pokemonDetails)
                } else {
                    PokemonDetailsLoadingErrorUiState(pokemonDetails)
                }
            }
        }
    }
}

sealed interface PokemonDetailsUiState {
    val detailedPokemon: Result<DetailedPokemon>?
}

data object PokemonDetailsLoadingUiState: PokemonDetailsUiState {
    override val detailedPokemon: Result<DetailedPokemon>? = null
}

data class PokemonDetailsLoadingErrorUiState(
    override val detailedPokemon: Result<DetailedPokemon> ): PokemonDetailsUiState

data class PokemonDetailsLoadingSuccessUiState(
    override val detailedPokemon: Result<DetailedPokemon>): PokemonDetailsUiState