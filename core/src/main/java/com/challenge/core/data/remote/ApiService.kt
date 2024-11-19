package com.challenge.core.data.remote

import com.challenge.core.data.remote.model.CharacterDetailDto
import com.challenge.core.data.remote.model.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterDto

    @GET("character/{character_id}")
    suspend fun getCharacterDetails(
        @Path("character_id") characterId: Int
    ): CharacterDetailDto

}
