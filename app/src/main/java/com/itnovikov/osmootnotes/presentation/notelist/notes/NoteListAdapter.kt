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
import com.itnovikov.osmootnotes.databinding.FragmentNoteListBinding
import com.itnovikov.osmootnotes.databinding.NoteItemBinding

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteListDiffCallback()) {

    inner class NoteViewHolder(private val binding: NoteItemBinding) : ViewHolder(binding.root) {

        fun bind(note: Note) = with(binding){
            textViewNoteTitle.text = note.title.trim()
            if (note.tags.trim().isEmpty()) textViewNoteTags.visibility = View.GONE

            textViewNoteTags.text = note.tags
            textViewNoteText.text = note.text.trim()
            buttonGoNote.setOnClickListener {
                onItemButtonClick?.invoke(note)
            }
        }
    }

    private var onItemButtonClick: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnItemButtonClick(function: ((Note) -> Unit)?) {
        onItemButtonClick = function
    }
}