package com.challenge.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.core.data.local.dao.CharacterDao
import com.challenge.core.data.local.dao.RemoteKeyDao
import com.challenge.core.data.local.model.CharacterEntity
import com.challenge.core.data.local.model.RemoteKeyEntity

@Database(
    entities = [CharacterEntity::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val remoteKeyDao: RemoteKeyDao
}
