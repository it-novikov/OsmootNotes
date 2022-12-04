package com.itnovikov.osmootnotes.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var uuid: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "tags")
    var tags: String = "",

    @ColumnInfo(name = "text")
    var text: String = "",

    @ColumnInfo(name = "dateOfCreation")
    var dateOfCreation: String = ""
)
