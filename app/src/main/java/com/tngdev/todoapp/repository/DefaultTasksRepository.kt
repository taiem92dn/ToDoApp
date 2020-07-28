package com.tngdev.todoapp.repository

import androidx.lifecycle.LiveData
import com.tngdev.todoapp.db.dao.TodoTaskDao
import com.tngdev.todoapp.model.TodoTask
import java.util.concurrent.Executors
import javax.inject.Inject

class DefaultTasksRepository @Inject constructor(
    private val todoTaskDao: TodoTaskDao
) : TasksRepository {
    override fun addNewTask(desc: String) {
        Executors.newSingleThreadExecutor().execute {
            todoTaskDao.insert(TodoTask(description = desc))
        }
    }

    override fun getAll() : LiveData<List<TodoTask>> {
        return todoTaskDao.getAll()
    }

    override fun getTasks(isComplete: Boolean) : LiveData<List<TodoTask>> {
        return todoTaskDao.getTasks(isComplete)
    }

    override fun updateTask(task: TodoTask) {
        Executors.newSingleThreadScheduledExecutor().execute {
            todoTaskDao.insert(task)
        }
    }
}