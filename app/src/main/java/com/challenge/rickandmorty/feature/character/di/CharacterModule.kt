package com.challenge.rickandmorty.feature.character.di

import com.challenge.core.data.repository.CharacterRepository
import com.challenge.rickandmorty.feature.character.domain.usecase.CharacterUseCase
import com.challenge.rickandmorty.feature.character.domain.usecase.CharacterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class CharacterDomainModule {

    @Provides
    @Singleton
    fun providesCharacterUseCase(repository: CharacterRepository): CharacterUseCase =
        CharacterUseCaseImpl(repository = repository)

}
