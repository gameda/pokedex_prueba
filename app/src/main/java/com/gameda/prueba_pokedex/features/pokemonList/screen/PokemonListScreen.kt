package com.gameda.prueba_pokedex.features.pokemonList.screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.features.common.composables.items.PokemonCardItem
import com.gameda.prueba_pokedex.features.common.composables.loading.LoadingSpinner
import com.gameda.prueba_pokedex.features.pokemonList.viewmodel.PokemonsViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun PokemonListScreen(
    pokemonListViewModel: PokemonsViewModel,
    itemClickedListener: (PokemonId) -> Unit = {}
) {
    PagedPokemonList(
        items = pokemonListViewModel.pokemonList.collectAsLazyPagingItems(),
        itemClickListener = itemClickedListener)
}

@Composable
fun PagedPokemonList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<SimplePokemon>,
    itemClickListener: (PokemonId) -> Unit
) {

    when(items.loadState.refresh) {
        is LoadState.Error -> {}
        is LoadState.Loading ->
            LoadingSpinner(modifier)
        is LoadState.NotLoading -> {}
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(
            items.itemCount,
            items.itemKey { it.id }
        ) { index ->
            val item = items[index]
            item?.let {
                PokemonCardItem(
                    pokemonId = item.id,
                    pokemonName = item.name,
                    pictureUrl = item.urlPicture,
                    onClick = { itemClickListener(item.id) }
                )
            }
        }
    }
}

@Preview
@Composable
fun previewPokemonListScreen() {
    PagedPokemonList(
        Modifier,
        flowOf(
            PagingData.from(
                listOf(
                    SimplePokemon(1, "Name1", ""),
                    SimplePokemon(1, "Name1", ""),
                    SimplePokemon(1, "Name1", ""),
                    SimplePokemon(1, "Name1", ""),
                    SimplePokemon(1, "Name1", "")
                )
            )
        ).collectAsLazyPagingItems()
    ) { _ -> }
}