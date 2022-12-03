package com.itnovikov.osmootnotes.presentation.detailednote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.itnovikov.osmootnotes.core.bases.BaseFragment
import com.itnovikov.osmootnotes.databinding.FragmentDetailedNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedNoteFragment : BaseFragment<FragmentDetailedNoteBinding, DetailedNoteViewModel>
    (FragmentDetailedNoteBinding::inflate) {

    override val viewModel: DetailedNoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureButton()
        setData()
    }

    private fun setData() {
        val noteData = arguments
        val title = noteData?.getString("title")
        val text = noteData?.getString("text")
        val tags = noteData?.getString("tags")
        val date = noteData?.getString("date")
        binding.textViewDetailedNoteTitle.text = title
        binding.textViewDetailedNoteText.text = text
        binding.textViewDetailedNoteTags.text = tags
        binding.textViewNoteCreationDate.text = date
    }

    private fun configureButton() {
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
    }

    private fun closeScreen() {
        viewModel.getShouldCloseScreen().observe(viewLifecycleOwner) {
            if (it) {
                navigateUp()
            }
        }
    }
}