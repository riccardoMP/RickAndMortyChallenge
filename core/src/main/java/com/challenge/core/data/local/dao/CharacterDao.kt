package com.challenge.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.core.data.local.model.CharacterEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CharacterEntity)

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME} WHERE id=:id")
    suspend fun getById(id: Int): CharacterEntity?

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME}")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM ${CharacterEntity.TABLE_NAME}")
    suspend fun clearAll()
}
