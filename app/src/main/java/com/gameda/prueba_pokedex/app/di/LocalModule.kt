package com.gameda.prueba_pokedex.app.di

import android.content.Context
import androidx.room.Room
import com.gameda.prueba_pokedex.data.local.db.PokedexDataBase
import com.gameda.prueba_pokedex.data.local.db.dao.DetailedPokemonDao
import com.gameda.prueba_pokedex.data.local.db.dao.PokedexDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PokedexDataBase =
        Room.databaseBuilder(context,
            PokedexDataBase::class.java,
            "pokedex_db")
            .build()


    @Singleton
    @Provides
    fun providePokemonDao(dataBase: PokedexDataBase): PokedexDao {
        return dataBase.pokemonDao
    }

    @Singleton
    @Provides
    fun providePokemonDetailDao(dataBase: PokedexDataBase): DetailedPokemonDao {
        return dataBase.detailedPokemonDao
    }
}