package com.gameda.prueba_pokedex.features.common.composables.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder
import com.gameda.prueba_pokedex.R

@Composable
fun AsyncImageUrl(url: String,
                  contentScale: ContentScale,
                  modifier: Modifier){

    var errorUrl = false

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        placeholder = painterResource(R.drawable.ic_loading_mini),
        contentScale = contentScale,
        onError = {
            errorUrl = true
        })

    Image(painter = if(!errorUrl) painter
        else painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = modifier
    )
}

