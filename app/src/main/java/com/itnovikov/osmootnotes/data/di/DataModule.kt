package com.itnovikov.osmootnotes.data.di

import com.itnovikov.osmootnotes.data.repository.NotesRepositoryImpl
import com.itnovikov.osmootnotes.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: NotesRepositoryImpl): NotesRepository
}