package com.itnovikov.osmootnotes.presentation.notelist.notes

import androidx.recyclerview.widget.DiffUtil
import com.itnovikov.osmootnotes.data.local.room.model.Note

class NoteListDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}