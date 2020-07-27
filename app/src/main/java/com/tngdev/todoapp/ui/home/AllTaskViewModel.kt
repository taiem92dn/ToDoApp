package com.tngdev.todoapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tngdev.todoapp.model.TodoTask
import com.tngdev.todoapp.repository.TodoTaskRepository

class AllTaskViewModel @ViewModelInject constructor(
    private val repository: TodoTaskRepository
): ViewModel() {

    lateinit var taskList: LiveData<List<TodoTask>>

    fun getAllTasks(): LiveData<List<TodoTask>> {
        if (!::taskList.isInitialized) {
            taskList = repository.getAll()
        }

        return taskList
    }
    fun getCompleteTasks(): LiveData<List<TodoTask>> {
        if (!::taskList.isInitialized) {
            taskList = repository.getTasks(true)
        }

        return taskList
    }
    fun getInCompleteTasks(): LiveData<List<TodoTask>> {
        if (!::taskList.isInitialized) {
            taskList = repository.getTasks(false)
        }

        return taskList
    }

    fun addNewTask(desc: String) {
        repository.addNewTask(desc)
    }

    fun updateTask(task: TodoTask) {
        repository.updateTask(task)
    }
}