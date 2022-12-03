package com.itnovikov.osmootnotes

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.itnovikov.osmootnotes.core.bases.BaseActivity
import com.itnovikov.osmootnotes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
}