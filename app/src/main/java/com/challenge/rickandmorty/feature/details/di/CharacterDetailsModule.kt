package com.challenge.rickandmorty.feature.details.di

import com.challenge.core.data.repository.CharacterRepository
import com.challenge.rickandmorty.feature.details.domain.usecase.CharacterDetailsUseCase
import com.challenge.rickandmorty.feature.details.domain.usecase.CharacterDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterDetailsModule {

    @Provides
    @Singleton
    fun providesCharacterUseCase(repository: CharacterRepository): CharacterDetailsUseCase =
        CharacterDetailsUseCaseImpl(repository = repository)
}
