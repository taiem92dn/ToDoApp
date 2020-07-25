package com.tngdev.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tngdev.todoapp.db.dao.TodoTaskDao
import com.tngdev.todoapp.model.TodoTask

@Database(entities = [TodoTask::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun pokemonDao(): TodoTaskDao
}