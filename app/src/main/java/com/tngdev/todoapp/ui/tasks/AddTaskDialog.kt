package com.tngdev.todoapp.ui.tasks

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.DialogFragment
import com.tngdev.todoapp.R
import com.tngdev.todoapp.databinding.DialogAddNewTaskBinding
import java.lang.IllegalStateException

class AddTaskDialog: DialogFragment() {

    var onAddNewTaskListener: OnAddNewTaskListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val binding = DialogAddNewTaskBinding.inflate(inflater)

            builder.setView(binding.root)
                .setTitle(R.string.dialog_new_task_title)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    if (!TextUtils.isEmpty(binding.etTaskDesc.text)) {
                        onAddNewTaskListener?.onAddNewTask(binding.etTaskDesc.text.toString())
                    }
                }
                .setNegativeButton(android.R.string.cancel) { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface OnAddNewTaskListener {
        fun onAddNewTask(desc: String)
    }


}