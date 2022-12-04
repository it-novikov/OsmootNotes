package com.itnovikov.osmootnotes.presentation.notelist.tags

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

class TagsAdapter : ListAdapter<Tag, TagsAdapter.TagFiltersViewHolder>(TagFiltersDiffCallback()) {

    class TagFiltersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filterButton: AppCompatButton = itemView.findViewById(R.id.buttonTag)
    }

    private var onFilterClick: ((Tag) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagFiltersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.tag_item,
            parent,
            false
        )
        return TagFiltersViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagFiltersViewHolder, position: Int) {
        val tag = getItem(position)
        holder.filterButton.text = tag.name
        holder.filterButton.setOnClickListener { onFilterClick?.invoke(tag) }
        if (!tag.isClicked) {
            setButtonUnclicked(holder)
        } else setButtonClicked(holder)
    }

    private fun setButtonClicked(viewHolder: TagFiltersViewHolder) {
        viewHolder.filterButton.setBackgroundResource(StatusTag.CLICKED_BG.res)

        viewHolder.filterButton.setTextColor(
            ContextCompat.getColor(
            viewHolder.filterButton.context,
            R.color.global_background
        ))

        val font: Typeface = viewHolder.filterButton.context.resources.getFont(R.font.proxima_nova_bold)
        viewHolder.filterButton.typeface = font

        viewHolder.filterButton.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(viewHolder.filterButton.context, R.drawable.ic_close),
            null
        )
    }

    private fun setButtonUnclicked(viewHolder: TagFiltersViewHolder) {
        viewHolder.filterButton.setBackgroundResource(StatusTag.UNCLICKED_BG.res)

        viewHolder.filterButton.setTextColor(
            ContextCompat.getColor(
            viewHolder.filterButton.context,
            R.color.dark_blue
        ))

        viewHolder.filterButton.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            null,
            null
        )
    }

    fun setOnFilterClick(function: ((Tag) -> Unit)?) {
        onFilterClick = function
    }

}