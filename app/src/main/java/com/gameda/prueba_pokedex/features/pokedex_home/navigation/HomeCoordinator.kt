package com.gameda.prueba_pokedex.features.pokedex_home.navigation

import androidx.navigation.NavHostController
import com.gameda.prueba_pokedex.app.navigation.DetailsScreen

class HomeCoordinator(private val navController: NavHostController
) {
    fun handleEvent(event: HomeNavigation) {
        when (event) {
            HomeNavigation.ToDetails ->
                navController.navigate(DetailsScreen)

            HomeNavigation.ToHome -> {}
        }
    }
}

sealed class HomeNavigation {
    object ToDetails : HomeNavigation()
    object ToHome : HomeNavigation()
}