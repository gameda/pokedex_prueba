package com.gameda.prueba_pokedex.app.di

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
object RepoModule {

    @Singleton
    @Provides
    fun providePokedexRepository(local: LocalDataSource, remote: RemoteDataSoruce): PokedexRepository {
        return PokedexRepositoryImpl(local, remote)
    }
}