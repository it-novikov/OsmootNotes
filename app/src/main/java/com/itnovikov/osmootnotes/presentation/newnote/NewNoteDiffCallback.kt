package com.itnovikov.osmootnotes.presentation.newnote

import androidx.recyclerview.widget.DiffUtil
import com.itnovikov.osmootnotes.data.local.room.model.Tag

class NewNoteDiffCallback : DiffUtil.ItemCallback<Tag>() {

    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }
}