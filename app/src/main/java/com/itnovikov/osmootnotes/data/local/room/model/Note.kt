package com.itnovikov.osmootnotes.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "tags")
    val tags: String = "",

    @ColumnInfo(name = "text")
    val text: String = "",

    @ColumnInfo(name = "dateOfCreation")
    val dateOfCreation: String = ""
)
