package com.gameda.prueba_pokedex.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.gameda.prueba_pokedex.domain.model.PokemonId
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data class DetailsScreen(val id: PokemonId)

@Serializable
sealed class BottomRoutes(
    val title: String,
    @Contextual
    val icon: ImageVector
) {
    @Serializable
    data object Home: BottomRoutes("Home", Icons.Default.Home)

    @Serializable
    data object Random: BottomRoutes("Random", Icons.Default.Person )

    @Serializable
    data object Favorites: BottomRoutes("Favorties", Icons.Default.Favorite)

}

val bottomItems = listOf(BottomRoutes.Home, BottomRoutes.Random, BottomRoutes.Favorites)





