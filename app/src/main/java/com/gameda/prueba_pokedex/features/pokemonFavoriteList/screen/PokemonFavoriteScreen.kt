package com.gameda.prueba_pokedex.features.pokemonFavoriteList.screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.domain.model.SimplePokemon
import com.gameda.prueba_pokedex.features.common.composables.items.PokemonCardItem
import com.gameda.prueba_pokedex.features.pokemonFavoriteList.viewmodel.PokemonsFavViewModel


@Composable
fun PokemonFavoriteScreen(
    viewModel: PokemonsFavViewModel,
    itemClickedListener: (PokemonId) -> Unit = {}
) {
    PagedPokemonList2(
        items = viewModel.pokemonList,
        itemClickListener = itemClickedListener
    )
}
@Composable
fun PagedPokemonList2(
    modifier: Modifier = Modifier,
    items: List<SimplePokemon>,
    itemClickListener: (PokemonId) -> Unit
) {


    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(items) { item ->
            PokemonCardItem(
                pokemonId = item.id,
                pokemonName = item.name,
                pictureUrl = item.urlPicture,
                onClick = { itemClickListener(item.id) }
            )
        }
    }
}