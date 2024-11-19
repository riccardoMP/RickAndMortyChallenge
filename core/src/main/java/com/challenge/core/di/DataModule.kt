package com.challenge.core.di

import androidx.paging.ExperimentalPagingApi
import com.challenge.core.data.local.LocalDatabase
import com.challenge.core.data.remote.ApiService
import com.challenge.core.data.repository.CharacterRepository
import com.challenge.core.data.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {


    @Provides
    @Singleton
    fun providesCharacterRepository(
        database: LocalDatabase,
        apiService: ApiService
    ): CharacterRepository =
        CharacterRepositoryImpl(database = database, apiService = apiService)

    /*@Provides
    @Singleton
    fun provideCharacterPager(
        localDatabase: LocalDatabase,
        apiService: ApiService,
    ): Pager<Int, CharacterEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharacterRemoteMediator(
                localDatabase = localDatabase,
                apiService = apiService,
            ),
            pagingSourceFactory = {
                localDatabase.characterDao.pagingSource()
            },
        )
    }

     */
}