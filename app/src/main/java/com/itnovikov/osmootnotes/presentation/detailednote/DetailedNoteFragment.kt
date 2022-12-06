package com.itnovikov.osmootnotes.presentation.detailednote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.itnovikov.osmootnotes.R
import com.itnovikov.osmootnotes.core.bases.BaseFragment
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.databinding.FragmentDetailedNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedNoteFragment : BaseFragment<FragmentDetailedNoteBinding, DetailedNoteViewModel>
    (FragmentDetailedNoteBinding::inflate) {

    override val viewModel: DetailedNoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        val noteData = arguments
        viewModel.getDetailedNote(noteData?.getString("id") ?: "").observe {
            binding.textViewDetailedNoteTitle.text = it.title
            binding.textViewDetailedNoteText.text = it.text
            binding.textViewDetailedNoteTags.text = it.tags
            binding.textViewDetailedNoteTags.visibility = View.VISIBLE
            binding.textViewNoteCreationDate.text = it.dateOfCreation

            configureButtons(it)
        }
    }

    private fun configureButtons(note: Note) {
        val noteData = arguments
        binding.buttonDeleteNote.setOnClickListener {
            val id = noteData?.getString("id")
            if (id != null) {
                viewModel.deleteNote(id)
                closeScreen()
            } else {
                showSnackbar("Error")
            }
        }

        binding.buttonEditNote.setOnClickListener { view ->
            val bundle = createBundle(note)
            navigateTo(R.id.newNoteFragment, bundle)
        }

        binding.buttonBackOnDetailedNote.setOnClickListener { navigateUp() }
    }

    private fun createBundle(note: Note): Bundle {
        val bundle = Bundle()
        bundle.putString("title", note.title)
        bundle.putString("text", note.text)
        bundle.putString("tags", note.tags)
        bundle.putString("date", note.dateOfCreation)
        bundle.putString("id", note.uuid)
        return bundle
    }

    private fun closeScreen() = viewModel.getShouldCloseScreen().observe() { if (it) navigateUp() }
}