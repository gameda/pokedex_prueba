package com.gameda.prueba_pokedex.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gameda.prueba_pokedex.data.local.db.dao.DetailedPokemonDao
import com.gameda.prueba_pokedex.data.local.db.dao.PokedexDao
import com.gameda.prueba_pokedex.data.local.db.entity.Converters
import com.gameda.prueba_pokedex.data.local.db.entity.DetailedPokemonEntity
import com.gameda.prueba_pokedex.data.local.db.entity.SimplePokemonEntity

@Database(entities = [SimplePokemonEntity::class,
                     DetailedPokemonEntity::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class PokedexDataBase: RoomDatabase() {

    abstract val pokemonDao: PokedexDao
    abstract val detailedPokemonDao: DetailedPokemonDao


}