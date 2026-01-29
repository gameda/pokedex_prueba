package com.gameda.prueba_pokedex.features.pokemonDetail.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gameda.prueba_pokedex.R
import com.gameda.prueba_pokedex.app.presentation.theme.Pink80
import com.gameda.prueba_pokedex.app.presentation.theme.White
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.features.common.composables.items.PokemonCardItem
import com.gameda.prueba_pokedex.features.common.composables.loading.LoadingSpinner
import com.gameda.prueba_pokedex.features.common.utils.enums.ScreenStatus
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.PokemonDetailsViewModel
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.UiEvent
import com.gameda.prueba_pokedex.features.pokemonDetail.viewmodel.UiState

@Composable
fun PokemonDetailsScreen(
    pokemonId: PokemonId,
    pokemonDetailsViewModel: PokemonDetailsViewModel
) {

    LaunchedEffect(Unit) {
        pokemonDetailsViewModel.getPokemonDetails(pokemonId)
    }

    val uiScreen = pokemonDetailsViewModel.uiScreen

    when (uiScreen) {
        ScreenStatus.LOADING ->
            LoadingSpinner()

        ScreenStatus.ERROR -> TODO()
        ScreenStatus.SUCCESS -> {
            val uiState = pokemonDetailsViewModel.uiState.collectAsState().value
            PokemonDetailsCardList(
                uiState = uiState,
                onEvent = pokemonDetailsViewModel::onEvent,
                retryCallback = { pokemonDetailsViewModel.getPokemonDetails(pokemonId) }
            )
        }
    }
}


@Composable
fun PokemonDetailsCardList(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onEvent: (UiEvent) -> Unit,
    retryCallback: () -> Unit
) {

    val pokemon = uiState.pokemonDetail
    var favoriteFlag by remember { mutableStateOf(pokemon?.isFavority ?: false) }


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_favorite),
            contentDescription = null,
            tint = if (favoriteFlag) Pink80 else White,
            modifier = Modifier
                .size(35.dp)
                .clickable {

                    favoriteFlag = !favoriteFlag
                    onEvent(UiEvent.OnFavoritePressed(pokemon!!.id, favoriteFlag))
                })


        pokemon?.let {
            PokemonCardItem(
                pokemonId = it.id,
                pokemonName = it.name,
                pictureUrl = it.urlPicture
            ) {}
        }



        ElevatedCard(
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                text = "Weight: ${pokemon?.weight}",
                textAlign = TextAlign.Left
            )
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                text = "Height: ${pokemon?.height}",
                textAlign = TextAlign.Left
            )
        }

        ElevatedCard(
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {


            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                text = "Types: ",
                textAlign = TextAlign.Left
            )

            pokemon?.types?.forEach {
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

