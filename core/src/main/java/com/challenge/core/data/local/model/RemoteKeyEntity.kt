package com.challenge.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RemoteKeyEntity.TABLE_NAME)
data class RemoteKeyEntity(
    @PrimaryKey val id: String,
    val nextPage: Int,
) {
    companion object {
        const val TABLE_NAME = "remote_key"
    }
}
