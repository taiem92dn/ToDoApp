package com.tngdev.todoapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoTask (
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    var description: String?,
    var isComplete: Boolean = false

)