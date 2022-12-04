package com.itnovikov.osmootnotes.presentation.notesettings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.itnovikov.osmootnotes.core.bases.BaseFragment
import com.itnovikov.osmootnotes.databinding.FragmentNotesSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesSettingsFragment
    :
    BaseFragment<FragmentNotesSettingsBinding, NotesSettingsViewModel>(FragmentNotesSettingsBinding::inflate) {

    override val viewModel: NotesSettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setChecked()
        super.onViewCreated(view, savedInstanceState)
        configureButtons()
        configureDarkThemeSwitch()
    }

    private fun configureButtons() {
        binding.buttonContactDeveloper.setOnClickListener { startActivity(viewModel.sendEmail()) }
        binding.buttonBackOnSettings.setOnClickListener { navigateUp() }
    }

    private fun configureDarkThemeSwitch() {
        binding.switchDarkTheme.setOnCheckedChangeListener { _, _ ->
            if (binding.switchDarkTheme.isChecked) viewModel.setDarkTheme(binding.switchDarkTheme.context)
            else viewModel.setLightTheme(binding.switchDarkTheme.context)
        }
    }

    private fun setChecked() {
        val prefs = activity?.getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE)
        binding.switchDarkTheme.isChecked = prefs?.getInt("switch_state_dark_mode", 0) == 1
    }
}