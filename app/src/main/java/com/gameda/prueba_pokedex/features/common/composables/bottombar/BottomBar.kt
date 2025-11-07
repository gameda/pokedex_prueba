package com.gameda.prueba_pokedex.features.common.composables.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gameda.prueba_pokedex.app.navigation.PokedexRoutes
import com.gameda.prueba_pokedex.app.navigation.bottomNavItems
import com.gameda.prueba_pokedex.features.pokemonList.navigation.navigateToPokemonList
import com.gameda.prueba_pokedex.features.pokemonRandom.navigation.navigateToPokemonRandom

@Composable
fun BottomBar(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        bottomNavItems.forEach { route ->
            val selected = currentDestination?.route == route::class.qualifiedName

            NavigationBarItem(
                selected = selected,
                onClick = {

                    when(route){
                        PokedexRoutes.Lista -> navController.navigateToPokemonList()
                        PokedexRoutes.Random -> navController.navigateToPokemonRandom()
                        else -> {navController.navigateToPokemonList()}
                    }

                },
                icon = { Icon( when(route) {
                        PokedexRoutes.Lista -> Icons.Filled.Home
                        PokedexRoutes.Random -> Icons.Filled.ThumbUp
                        PokedexRoutes.Favorities -> Icons.Filled.Favorite
                        else -> Icons.Filled.AddCircle
                    }, contentDescription = route.title) },
                label = { Text(route.title) }
            )
        }
    }
}