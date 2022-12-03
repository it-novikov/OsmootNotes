package com.itnovikov.osmootnotes.presentation.notelist.model

import com.itnovikov.osmootnotes.data.local.room.model.Tag

data class MainFilter(
    var isActive: Boolean = false,
    val listTags: MutableList<Tag> = mutableListOf()
)