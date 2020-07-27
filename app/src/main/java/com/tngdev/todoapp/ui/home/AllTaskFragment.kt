package com.tngdev.todoapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.tngdev.todoapp.R
import com.tngdev.todoapp.databinding.FragmentAllTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_task.*

@AndroidEntryPoint
class AllTaskFragment : Fragment() {

    enum class Type {
        ALL, INCOMPLETE, COMPLETE
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Type=Type.ALL): AllTaskFragment {
            val fragment = AllTaskFragment()
            val bundle = Bundle()
            bundle.putInt("type", type.ordinal)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var allTaskViewModel: AllTaskViewModel

    lateinit var binding: FragmentAllTaskBinding
    private var newTaskDialog: AddTaskDialog? = null
    lateinit var todoTaskAdapter: TodoTaskAdapter
    private var type: Type = Type.ALL

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allTaskViewModel =
                ViewModelProvider(this).get(AllTaskViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all_task, container, false)
        binding = FragmentAllTaskBinding.bind(root)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let {
            type = Type.values()[bundle.getInt("type", 0)]
        }

        setupView()
    }

    private fun setupView() {

        val tasks = when(type) {
            Type.ALL -> allTaskViewModel.getAllTasks()
            Type.COMPLETE -> allTaskViewModel.getCompleteTasks()
            Type.INCOMPLETE -> allTaskViewModel.getInCompleteTasks()
        }

        todoTaskAdapter = TodoTaskAdapter()
        binding.rvALLTask.adapter = todoTaskAdapter

        todoTaskAdapter.onTaskCompleteChangeListener =
            object : TodoTaskAdapter.OnTaskCompleteChangeListener {
                override fun onChange(isComplete: Boolean, position: Int) {
                    val item = todoTaskAdapter.data?.get(position) ?: return
                    item.isComplete = isComplete
                    allTaskViewModel.updateTask(item)
                }
            }

        tasks.observe(viewLifecycleOwner) {
            todoTaskAdapter.data = it
            todoTaskAdapter.notifyDataSetChanged()
        }

        if (type != Type.INCOMPLETE) {
            binding.fabNewTask.visibility = View.GONE
            return
        }

        if (newTaskDialog == null){
            newTaskDialog = AddTaskDialog()
        }

        newTaskDialog?.onAddNewTaskListener = object : AddTaskDialog.OnAddNewTaskListener {
            override fun onAddNewTask(desc: String) {
                allTaskViewModel.addNewTask(desc)
            }
        }

        binding.fabNewTask.setOnClickListener {
            newTaskDialog?.show(parentFragmentManager, null)
        }
    }
}