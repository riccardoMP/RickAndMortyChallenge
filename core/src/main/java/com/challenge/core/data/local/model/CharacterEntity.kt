package com.challenge.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CharacterEntity.TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey val id: Int,

    val name: String,

    @ColumnInfo("image_url")
    val imageUrl: String,

    @ColumnInfo("gender")
    val gender: String,

    @ColumnInfo("status")
    val status: String,

    @ColumnInfo("species")
    val species: String,

    ) {
    companion object {
        const val TABLE_NAME = "character_table"
    }
}
