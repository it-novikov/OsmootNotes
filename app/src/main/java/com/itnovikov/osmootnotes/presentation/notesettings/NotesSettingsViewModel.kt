package com.itnovikov.osmootnotes.presentation.notesettings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesSettingsViewModel @Inject constructor() : ViewModel() {

    fun sendEmail(): Intent {
        val uri = Uri.parse("mailto:nvkv71@gmail.com")
        return Intent(Intent.ACTION_SENDTO, uri)
    }

    fun setDarkTheme(context: Context) {
        val editor = context.getSharedPreferences("prefs", Context.MODE_PRIVATE).edit()
        with (editor) {
            putInt("dark_mode", 1)
            apply()
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun setLightTheme(context: Context) {
        val editor = context.getSharedPreferences("prefs", Context.MODE_PRIVATE).edit()
        with (editor) {
            putInt("dark_mode", 0)
            apply()
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}