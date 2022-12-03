package com.itnovikov.osmootnotes.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.data.local.room.model.Tag

@Database(entities = [Note::class, Tag::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}