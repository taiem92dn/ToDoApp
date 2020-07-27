package com.tngdev.todoapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tngdev.todoapp.model.TodoTask

@Dao
interface TodoTaskDao {

    @Query("SELECT * FROM todotask")
    fun getAll(): LiveData<List<TodoTask>>

    @Query("SELECT * FROM todotask WHERE isComplete = :isComplete")
    fun getTasks(isComplete: Boolean): LiveData<List<TodoTask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todoTask: TodoTask)

    @Insert
    fun insertAll(todoTasks: List<TodoTask>)

    @Delete
    fun delete(todoTask: TodoTask)
}