package com.tngdev.todoapp.di.module

import android.app.Application
import androidx.room.Room
import com.tngdev.todoapp.db.AppDatabase
import com.tngdev.todoapp.db.dao.TodoTaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDB(app: Application) : AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "TodoAppDB").build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: AppDatabase) : TodoTaskDao {
        return appDatabase.pokemonDao()
    }
}