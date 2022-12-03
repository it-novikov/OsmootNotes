package com.itnovikov.osmootnotes.presentation.notelist.tags

import androidx.recyclerview.widget.DiffUtil
import com.itnovikov.osmootnotes.data.local.room.model.Tag

class TagFiltersDiffCallback : DiffUtil.ItemCallback<Tag>() {

    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }
}