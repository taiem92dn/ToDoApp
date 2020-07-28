package com.tngdev.todoapp.di.module

import com.tngdev.todoapp.repository.DefaultTasksRepository
import com.tngdev.todoapp.repository.TasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTasksRepository(defaultTasksRepository: DefaultTasksRepository): TasksRepository {
        return defaultTasksRepository
    }
}