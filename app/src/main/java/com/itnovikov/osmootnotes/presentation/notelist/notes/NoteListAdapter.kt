package com.itnovikov.osmootnotes.presentation.notelist.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.itnovikov.osmootnotes.R
import com.itnovikov.osmootnotes.data.local.room.model.Note

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteListDiffCallback()) {

    class NoteViewHolder(itemView: View) : ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewNoteTitle)
        val tags: TextView = itemView.findViewById(R.id.textViewNoteTags)
        val description: TextView = itemView.findViewById(R.id.textViewNoteText)
        val buttonGoNote: AppCompatButton = itemView.findViewById(R.id.buttonGoNote)
    }

    private var onItemButtonClick: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item,
            parent,
            false
        )
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.title.text = note.title.trim()
        if (note.tags.trim().isEmpty()) {
            holder.tags.visibility = View.GONE
        }
        holder.tags.text = note.tags
        holder.description.text = note.text.trim()
        holder.buttonGoNote.setOnClickListener {
            onItemButtonClick?.invoke(note)
        }
    }

    fun setOnItemButtonClick(function: ((Note) -> Unit)?) {
        onItemButtonClick = function
    }
}