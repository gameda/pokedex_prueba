package com.gameda.prueba_pokedex.app.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gameda.prueba_pokedex.app.navigation.BottomBar

@Composable
fun PokeAppScaffold(modifier: Modifier = Modifier,
                    navController: NavController,
                    content: @Composable (PaddingValues) -> Unit) {

    Scaffold (
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        bottomBar = {
            BottomBar(navController)

        },
        content = { content(it) }

    )

}
