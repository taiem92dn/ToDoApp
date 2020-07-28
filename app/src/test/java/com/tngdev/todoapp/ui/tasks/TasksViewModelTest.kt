package com.tngdev.todoapp.ui.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tngdev.todoapp.FakeTestRepository
import com.tngdev.todoapp.ui.getOrAwaitValue
import com.tngdev.todoapp.ui.observeForTesting
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule

class TasksViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    private lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setupViewModel() {
        tasksRepository = FakeTestRepository()
        tasksRepository.addNewTask("Description1")
        tasksRepository.addNewTask("Description2")
        tasksRepository.addNewTask("Description3")

        tasksViewModel = TasksViewModel(tasksRepository)
    }

    @Test
    fun getAllTasksFromRepository() {
        tasksViewModel.getAllTasks()

        tasksViewModel.taskList.observeForTesting {
            assertEquals(tasksViewModel.taskList.getOrAwaitValue().size, 3)
        }
    }

    @Test
    fun updateTaskToRepository() {
        tasksViewModel.getAllTasks()

        tasksViewModel.taskList.observeForTesting {
            val task = tasksViewModel.taskList.getOrAwaitValue()
            task[0].isComplete = true

            tasksViewModel.updateTask(task[0])

            tasksViewModel.getCompleteTasks()

            assertEquals(tasksViewModel.taskList.getOrAwaitValue().size, 1)
        }
    }

    @Test
    fun getIncompleteTasksFromRepository() {
        tasksViewModel.getInCompleteTasks()

        tasksViewModel.taskList.observeForTesting {
            assertEquals(tasksViewModel.taskList.getOrAwaitValue().size, 3)
        }
    }

    @Test
    fun getCompleteTasksFromRepository() {
        tasksViewModel.getCompleteTasks()

        tasksViewModel.taskList.observeForTesting {
            assertEquals(tasksViewModel.taskList.getOrAwaitValue().size, 0)
        }
    }
}