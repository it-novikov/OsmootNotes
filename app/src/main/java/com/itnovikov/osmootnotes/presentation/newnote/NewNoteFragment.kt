package com.itnovikov.osmootnotes.presentation.newnote

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.itnovikov.osmootnotes.R
import com.itnovikov.osmootnotes.core.bases.BaseFragment
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.data.local.room.model.Tag
import com.itnovikov.osmootnotes.databinding.FragmentNewNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewNoteFragment
    : BaseFragment<FragmentNewNoteBinding, NewNoteViewModel>(FragmentNewNoteBinding::inflate) {

    override val viewModel: NewNoteViewModel by viewModels()
    private val adapter = NewNoteAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        observeViewModel()
        configureButtons()
    }

    private fun initRV() {
        binding.rvTags.adapter = adapter
        initCallbacks()
    }

    private fun initCallbacks() {
        adapter.setOnTagClick {
            if (it.isClicked) {
                viewModel.clickTag(it, !it.isClicked)
                viewModel.removeTagFromNote(it)
            } else {
                viewModel.clickTag(it, !it.isClicked)
                viewModel.newTagToNote(it)
            }
            binding.rvTags.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.tags.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun configureButtons() {
        configureTagButtons()
        configureSaveNoteButton()
        configureAddTagButton()
    }

    private fun configureAddTagButton() {
        binding.buttonAddTag.setOnClickListener { createAlertDialog() }
    }

    private fun configureTagButtons() {

    }

    private fun configureSaveNoteButton() {
        binding.buttonSaveNote.setOnClickListener {
            val title = binding.editTextNoteTitle.text.toString().trim()
            val description = binding.editTextNoteDescription.text.toString().trim()
            val tags = viewModel.getTagsList()
            val currentDate = getString(R.string.date_of_creation) + viewModel.getCurrentDate()
            if (title.isNotEmpty()) {
                val note = Note(
                    title = title,
                    text = description,
                    tags = tags,
                    dateOfCreation = currentDate
                )
                lifecycleScope.launch { viewModel.addNote(note) }
            } else showSnackbar(getString(R.string.title_is_empty))

            closeScreen()
        }
    }

    private fun createAlertDialog() {
        val builder = AlertDialog.Builder(context)

        val alertDialogView: View = layoutInflater.inflate(R.layout.alert_dialog_new_tag, null)

        val emptyName = alertDialogView.findViewById<TextView>(R.id.textViewEmptyTagName)

        builder.setView(alertDialogView)
        builder.setCancelable(true)
        val alert = builder.create()

        val addButton = alertDialogView.findViewById<AppCompatButton>(R.id.buttonAddNewTag)
        addButton.setOnClickListener {
            val tagName = alertDialogView.findViewById<TextInputEditText>(R.id.editTextNewTag)
            val tag = Tag(name = tagName.text.toString().trim(), isClicked = false)
            if (tagName.text?.trim()?.isNotEmpty() == true) {
                viewModel.addTag(tag)
                emptyName.visibility = View.GONE
                alert.cancel()
            } else emptyName.visibility = View.VISIBLE
        }

        val dismissButton = alertDialogView.findViewById<AppCompatButton>(R.id.buttonDenyNewTag)
        dismissButton.setOnClickListener {
            alert.cancel()
            emptyName.visibility = View.GONE
        }

        alert.show()
    }

    private fun closeScreen() {
        viewModel.getShouldCloseScreen().observe(viewLifecycleOwner) {
            if (it) {
                navigateUp()
            }
        }
    }
}