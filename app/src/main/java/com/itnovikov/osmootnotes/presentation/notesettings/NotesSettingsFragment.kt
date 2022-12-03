package com.itnovikov.osmootnotes.presentation.notesettings

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.itnovikov.osmootnotes.core.bases.BaseFragment
import com.itnovikov.osmootnotes.databinding.FragmentNotesSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesSettingsFragment
    : BaseFragment<FragmentNotesSettingsBinding, NotesSettingsViewModel>(FragmentNotesSettingsBinding::inflate) {

    override val viewModel: NotesSettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureButton()
        configureDarkThemeSwitch()
    }

    private fun configureButton() {
        binding.buttonContactDeveloper.setOnClickListener { startActivity(viewModel.sendEmail()) }
    }

    private fun configureDarkThemeSwitch() {
        val prefs = binding
            .switchDarkTheme
            .context
            .getSharedPreferences("prefs", Context.MODE_PRIVATE)
        binding.switchDarkTheme.isChecked = prefs.getInt("dark_mode", 0) == 1

        binding.switchDarkTheme.setOnCheckedChangeListener {_, isChecked ->
            if (binding.switchDarkTheme.isChecked) {
                viewModel.setDarkTheme(binding.switchDarkTheme.context)
            } else {
                viewModel.setLightTheme(binding.switchDarkTheme.context)
            }
        }
    }
}