package com.itnovikov.osmootnotes.presentation.newnote

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itnovikov.osmootnotes.R
import com.itnovikov.osmootnotes.data.local.room.model.Tag
import com.itnovikov.osmootnotes.presentation.core.StatusTag

class NewNoteAdapter : ListAdapter<Tag, NewNoteAdapter.TagsViewHolder>(NewNoteDiffCallback()) {

    class TagsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tagButton: AppCompatButton = itemView.findViewById(R.id.buttonTag)
    }

    private var onTagClick: ((Tag) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.tag_item,
            parent,
            false
        )
        return TagsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        val tag = getItem(position)
        holder.tagButton.text = tag.name
        holder.tagButton.setOnClickListener { onTagClick?.invoke(tag) }
        if (tag.isClicked) setButtonClicked(holder)
        else setButtonUnclicked(holder)
    }

    fun setOnTagClick(function: ((Tag) -> Unit)?) {
        onTagClick = function
    }

    private fun setButtonClicked(viewHolder: TagsViewHolder) {
        viewHolder.tagButton.setBackgroundResource(StatusTag.CLICKED_BG.res)

        viewHolder.tagButton.setTextColor(ContextCompat.getColor(
            viewHolder.tagButton.context,
            R.color.global_background
        ))

        val font: Typeface = viewHolder.tagButton.context.resources.getFont(R.font.proxima_nova_bold)
        viewHolder.tagButton.typeface = font

        viewHolder.tagButton.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(viewHolder.tagButton.context, R.drawable.ic_close),
            null
        )
    }

    private fun setButtonUnclicked(viewHolder: TagsViewHolder) {
        viewHolder.tagButton.setBackgroundResource(StatusTag.UNCLICKED_BG.res)

        viewHolder.tagButton.setTextColor(ContextCompat.getColor(
            viewHolder.tagButton.context,
            R.color.dark_blue
        ))

        viewHolder.tagButton.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            null,
            null
        )
    }
}