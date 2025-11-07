package com.gameda.prueba_pokedex.app.di

import com.gameda.prueba_pokedex.data.local.LocalDataSourceImpl
import com.gameda.prueba_pokedex.data.local.db.dao.DetailedPokemonDao
import com.gameda.prueba_pokedex.data.local.db.dao.PokedexDao
import com.gameda.prueba_pokedex.data.remote.RemoteDataSourceImpl
import com.gameda.prueba_pokedex.data.remote.api.PokedexApiService
import com.gameda.prueba_pokedex.data.repository.PokedexRepositoryImpl
import com.gameda.prueba_pokedex.data.repository.contracts.LocalDataSource
import com.gameda.prueba_pokedex.data.repository.contracts.RemoteDataSoruce
import com.gameda.prueba_pokedex.domain.repository.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(pokedexDao: PokedexDao,
                                detailDao: DetailedPokemonDao): LocalDataSource {
        return LocalDataSourceImpl(pokedexDao, detailDao)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(api: PokedexApiService): RemoteDataSoruce {
        return RemoteDataSourceImpl(api)
    }


}