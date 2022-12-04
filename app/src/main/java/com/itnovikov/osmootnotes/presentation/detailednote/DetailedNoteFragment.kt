package com.itnovikov.osmootnotes.presentation.detailednote

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.itnovikov.osmootnotes.R
import com.itnovikov.osmootnotes.core.bases.BaseFragment
import com.itnovikov.osmootnotes.core.extension.log
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.databinding.FragmentDetailedNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedNoteFragment : BaseFragment<FragmentDetailedNoteBinding, DetailedNoteViewModel>
    (FragmentDetailedNoteBinding::inflate) {

    override val viewModel: DetailedNoteViewModel by viewModels()

    private var idOfCurrentNote: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureButtons()
        setData()
    }

    private fun setData() {
        val noteData = arguments
        val title = noteData?.getString("title")
        val text = noteData?.getString("text")
        val tags = noteData?.getString("tags")
        val date = noteData?.getString("date")
        val id = noteData?.getString("id")
        idOfCurrentNote = id?.trim()?.toInt() ?: 0
        log(message = "idOfCurrentNote = $idOfCurrentNote")
        binding.textViewDetailedNoteTitle.text = title
        binding.textViewDetailedNoteText.text = text
        if (tags == null) {
            binding.textViewDetailedNoteTags.visibility = View.GONE
        } else {
            binding.textViewDetailedNoteTags.text = tags
            binding.textViewDetailedNoteTags.visibility = View.VISIBLE
        }
        binding.textViewNoteCreationDate.text = date
    }

    private fun configureButtons() {
        val noteData = arguments
        binding.buttonDeleteNote.setOnClickListener {
            val id = noteData?.getInt("id")
            if (id != null) {
                viewModel.deleteNote(id)
                closeScreen()
            } else {
                showSnackbar("Error")
            }
        }

        binding.buttonBackOnDetailedNote.setOnClickListener { navigateUp() }

        binding.buttonEditNote.setOnClickListener {
            val title = noteData?.getString("title")
            val text = noteData?.getString("text")
            val tags = noteData?.getString("tags")
            val date = noteData?.getString("date")
            val note = Note(
                id = idOfCurrentNote,
                title = title ?: "",
                text = text ?: "",
                tags = tags ?: "",
                dateOfCreation = date ?: ""
            )

            val bundle = createBundle(note)
            navigateTo(R.id.newNoteFragment, bundle)
        }
    }

    private fun createBundle(note: Note): Bundle {
        val bundle = Bundle()
        bundle.putString("title", note.title)
        bundle.putString("text", note.text)
        bundle.putString("tags", note.tags)
        bundle.putString("date", note.dateOfCreation)
        bundle.putInt("id", note.id)
        return bundle
    }

    private fun closeScreen() = viewModel.getShouldCloseScreen().observe() { if (it) navigateUp() }
}