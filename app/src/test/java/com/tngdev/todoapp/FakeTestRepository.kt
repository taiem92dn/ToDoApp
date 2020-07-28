package com.tngdev.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tngdev.todoapp.model.TodoTask
import com.tngdev.todoapp.repository.TasksRepository

class FakeTestRepository : TasksRepository {

    var tasksServiceData: MutableList<TodoTask> = ArrayList()

    private val observable = MutableLiveData<List<TodoTask>>()

    override fun addNewTask(desc: String) {
        val task = TodoTask(id = tasksServiceData.size+1, description = desc)
        tasksServiceData.add(task)
    }

    override fun getAll(): LiveData<List<TodoTask>> {
        observable.value = tasksServiceData
        return observable
    }

    override fun getTasks(isComplete: Boolean): LiveData<List<TodoTask>> {
        observable.value = tasksServiceData.filter { it.isComplete == isComplete }
        return observable
    }

    override fun updateTask(task: TodoTask) {
        tasksServiceData[task.id-1] = task
    }
}