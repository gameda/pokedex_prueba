package com.gameda.prueba_pokedex.data.remote.api

import com.gameda.prueba_pokedex.data.remote.model.PokemonDetailsDTO
import com.gameda.prueba_pokedex.data.remote.model.PokemonListResponse
import com.gameda.prueba_pokedex.domain.model.PokemonId
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApiService {
    @GET("pokemon/")
    suspend fun getPokemonsList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
        ): PokemonListResponse

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetails(
        @Path("id") id: PokemonId
    ): PokemonDetailsDTO
}