package com.gameda.prueba_pokedex.data.local.db.entity

import androidx.room.TypeConverter
import com.gameda.prueba_pokedex.domain.model.PokemonType
import com.gameda.prueba_pokedex.utils.parseList
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromPokemonTypeList(list: List<PokemonType>): String = Gson().toJson(list)

    @TypeConverter
    fun toPokemonPokemonTypeList(list: String): List<PokemonType> = Gson().parseList(list, PokemonType::class.java) ?: emptyList()
}