package com.gameda.prueba_pokedex.features.pokemonDetail.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.features.common.composables.items.PokemonCardItem
import com.gameda.prueba_pokedex.features.common.composables.loading.LoadingSpinner
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.PokemonDetailsLoadingErrorUiState
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.PokemonDetailsLoadingSuccessUiState
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.PokemonDetailsLoadingUiState
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.PokemonDetailsUiState
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.PokemonDetailsViewModel

@Composable
fun PokemonDetailsScreen(
    pokemonId: PokemonId,
    pokemonDetailsViewModel: PokemonDetailsViewModel
) {
    LaunchedEffect(Unit) { pokemonDetailsViewModel.getPokemonDetails(pokemonId) }
    PokemonDetailsCardList(
        uiState = pokemonDetailsViewModel.uiState.observeAsState(),
        retryCallback = { pokemonDetailsViewModel.getPokemonDetails(pokemonId) }
    )
}

@Composable
fun PokemonDetailsCardList(
    modifier: Modifier = Modifier,
    uiState: State<PokemonDetailsUiState?>,
    retryCallback: () -> Unit
) {

    when(uiState.value) {
        null, is PokemonDetailsLoadingUiState -> {
            LoadingSpinner(modifier)
        }
        is PokemonDetailsLoadingErrorUiState -> {}
        is PokemonDetailsLoadingSuccessUiState -> {
            val pokemon = uiState.value!!.detailedPokemon!!.getOrNull()!!
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
            ) {
                PokemonCardItem(
                    pokemonId = pokemon.id,
                    pokemonName = pokemon.name,
                    pictureUrl = pokemon.urlPicture
                ) {}

                ElevatedCard(modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                ) {

                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        text = "Weight: ${pokemon.weight}",
                        textAlign = TextAlign.Left
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        text = "Height: ${pokemon.height}",
                        textAlign = TextAlign.Left
                    )
                }

                ElevatedCard(modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                ) {

                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        text = "Types",
                        textAlign = TextAlign.Left
                    )

                    pokemon.types.forEach {
                        Text(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
                                .border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp),
                            text = it.name,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
    }

}

