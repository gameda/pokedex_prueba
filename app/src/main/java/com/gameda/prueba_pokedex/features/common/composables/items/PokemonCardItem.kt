
package com.gameda.prueba_pokedex.features.common.composables.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gameda.prueba_pokedex.domain.model.PokemonId
import com.gameda.prueba_pokedex.features.common.composables.images.AsyncImageUrl
import java.util.Locale

@Composable
fun PokemonCardItem(
    modifier: Modifier = Modifier,
    pokemonId: PokemonId,
    pokemonName: String,
    pictureUrl: String?,
    onClick: (PokemonId) -> Unit
) {
    ElevatedCard(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable{
            onClick(pokemonId)
        }
    ) {
        CardContent(pokemonId, pokemonName, pictureUrl)
    }
}

@Composable
private fun CardContent(
    pokemonId: PokemonId,
    pokemonName: String,
    pictureUrl: String?) {

    Row(
        modifier = Modifier.padding(5.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            text = pokemonId.toString(),
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = pokemonName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            style = MaterialTheme.typography.titleLarge
        )

        AsyncImageUrl(
            url = pictureUrl ?: " ",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(4.dp)
                .size(70.dp)
        )


    }

}


@Preview
@Composable
fun headerCardPreview() {
    PokemonCardItem(
        pokemonId = 1,
        pokemonName = "Charizard",
        pictureUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
    ){

    }
}
