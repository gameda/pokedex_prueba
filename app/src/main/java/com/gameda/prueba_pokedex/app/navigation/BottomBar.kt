package com.gameda.prueba_pokedex.app.navigation

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
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        bottomItems.forEach { item ->
            val selected = currentDestination?.route == item::class.qualifiedName

            NavigationBarItem(
                selected = selected,
                onClick = {

                    navController.navigate(item) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                icon = { Icon(item.icon, contentDescription = null) },

                label = { Text(item.title) }
            )
        }
    }
}