package com.tngdev.todoapp.repository

import androidx.lifecycle.LiveData
import com.tngdev.todoapp.model.TodoTask

interface TasksRepository {
    fun addNewTask(desc: String)
    fun getAll() : LiveData<List<TodoTask>>
    fun getTasks(isComplete: Boolean) : LiveData<List<TodoTask>>
    fun updateTask(task: TodoTask)
}