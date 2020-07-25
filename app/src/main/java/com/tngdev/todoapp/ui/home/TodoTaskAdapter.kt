package com.tngdev.todoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.tngdev.todoapp.databinding.ItemTodoTaskBinding
import com.tngdev.todoapp.model.TodoTask

class TodoTaskAdapter : RecyclerView.Adapter<TodoTaskAdapter.TodoTaskVH>() {

    class TodoTaskVH(val binding : ItemTodoTaskBinding) : RecyclerView.ViewHolder(binding.root)

    var data : List<TodoTask>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoTaskVH {
        val binding = ItemTodoTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoTaskVH(binding)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: TodoTaskVH, position: Int) {
        val item = data?.get(position) ?: return

        holder.binding.cbCompleteTask.isChecked = item.isComplete
        holder.binding.tvDescTask.text = item.description

        holder.binding.cbCompleteTask.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->

        }
    }


}