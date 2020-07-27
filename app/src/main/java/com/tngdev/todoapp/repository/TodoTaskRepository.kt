package com.tngdev.todoapp.repository

import androidx.lifecycle.LiveData
import com.tngdev.todoapp.db.dao.TodoTaskDao
import com.tngdev.todoapp.model.TodoTask
import java.util.concurrent.Executors
import javax.inject.Inject

class TodoTaskRepository @Inject constructor(
    private val todoTaskDao: TodoTaskDao
) {
    fun addNewTask(desc: String) {
        Executors.newSingleThreadExecutor().execute {
            todoTaskDao.insert(TodoTask(description = desc))
        }
    }

    fun getAll() : LiveData<List<TodoTask>> {
        return todoTaskDao.getAll()
    }

    fun getTasks(isComplete: Boolean) : LiveData<List<TodoTask>> {
        return todoTaskDao.getTasks(isComplete)
    }

    fun updateTask(task: TodoTask) {
        Executors.newSingleThreadScheduledExecutor().execute {
            todoTaskDao.insert(task)
        }
    }
}