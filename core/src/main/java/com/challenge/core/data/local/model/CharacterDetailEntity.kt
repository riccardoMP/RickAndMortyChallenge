package com.challenge.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CharacterEntity.TABLE_NAME)
data class CharacterDetailEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo("gender")
    val gender: String,

    @ColumnInfo("image_url")
    val imageUrl: String,

    @ColumnInfo("location")
    val location: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("origin")
    val origin: String,

    @ColumnInfo("status")
    val status: String,

    @ColumnInfo("species")
    val species: String,

    @ColumnInfo("type")
    val type: String,

    ) {
    companion object {
        const val TABLE_NAME = "character_detail_table"
    }
}
