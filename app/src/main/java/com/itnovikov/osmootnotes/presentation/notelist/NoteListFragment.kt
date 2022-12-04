package com.itnovikov.osmootnotes.presentation.notelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.itnovikov.osmootnotes.R
import com.itnovikov.osmootnotes.core.bases.BaseFragment
import com.itnovikov.osmootnotes.data.local.room.model.Note
import com.itnovikov.osmootnotes.databinding.FragmentNoteListBinding
import com.itnovikov.osmootnotes.presentation.notelist.notes.NoteListAdapter
import com.itnovikov.osmootnotes.presentation.notelist.tags.TagsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment
    : BaseFragment<FragmentNoteListBinding, NoteListViewModel>(FragmentNoteListBinding::inflate) {

    override val viewModel: NoteListViewModel by viewModels()
    private val notesAdapter = NoteListAdapter()
    private val tagFiltersAdapter = TagsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        observeViewModel()
        configureButtons()
    }

    private fun initRV() = with(binding) {
        rvNotes.adapter = notesAdapter
        rvTagFilters.adapter = tagFiltersAdapter
        initCallbacks()
    }

    private fun initCallbacks() {
        notesAdapter.setOnItemButtonClick {
            val bundle = createBundle(it)
            navigateTo(R.id.detailedNoteFragment, bundle)
        }

        tagFiltersAdapter.setOnFilterClick {
            if (it.isClicked) {
                viewModel.clickTag(it, false)
                viewModel.updateFilter(tag = Pair(it, false))
            } else {
                viewModel.clickTag(it, true)
                viewModel.updateFilter(tag = Pair(it, true))
            }
            binding.rvTagFilters.adapter = tagFiltersAdapter
        }
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

    private fun observeViewModel() {
        viewModel.tagFilters.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvTagFilters.visibility = View.GONE
            } else {
                tagFiltersAdapter.submitList(it)
                binding.rvTagFilters.visibility = View.VISIBLE
            }
        }

        viewModel.notes.observe {
            if (it.isEmpty()) {
                hideRV()
                binding.textViewEmptyTagList.visibility = View.VISIBLE
            } else {
                binding.textViewEmptyTagList.visibility = View.GONE
                showRV(it)
            }
        }

        viewModel.dataFilter.observe {
            val data: List<Note>? = if(it.listTags.size == 0) viewModel.getStartNotes()
            else viewModel.filteredListNote(it)

            val filteredListNote: List<Note>? = data

            if (filteredListNote != null) {
                notesAdapter.submitList(filteredListNote)
            }
        }
    }

    private fun showRV(it: List<Note>) {
        binding.textViewEmptyTagList.visibility = View.GONE
        binding.rvNotes.visibility = View.VISIBLE
        notesAdapter.submitList(it)
    }

    private fun hideRV() {
        binding.textViewEmptyTagList.visibility = View.VISIBLE
        binding.rvNotes.visibility = View.GONE
    }

    private fun configureButtons() {
        binding.fabAddNote.setOnClickListener {
            navigateTo(R.id.newNoteFragment)
        }

        binding.buttonSettings.setOnClickListener {
            navigateTo(R.id.notesSettingsFragment)
        }
    }
}