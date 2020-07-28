package com.tngdev.todoapp.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tngdev.todoapp.model.TodoTask
import com.tngdev.todoapp.repository.TasksRepository

class TasksViewModel @ViewModelInject constructor(
    private val repository: TasksRepository
): ViewModel() {

    lateinit var taskList: LiveData<List<TodoTask>>

    fun getAllTasks(): LiveData<List<TodoTask>> {
        taskList = repository.getAll()

        return taskList
    }
    fun getCompleteTasks(): LiveData<List<TodoTask>> {
        taskList = repository.getTasks(true)

        return taskList
    }
    fun getInCompleteTasks(): LiveData<List<TodoTask>> {

        taskList = repository.getTasks(false)

        return taskList
    }

    fun addNewTask(desc: String) {
        repository.addNewTask(desc)
    }

    fun updateTask(task: TodoTask) {
        repository.updateTask(task)
    }
}