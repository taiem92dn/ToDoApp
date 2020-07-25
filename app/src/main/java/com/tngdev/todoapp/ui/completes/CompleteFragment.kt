package com.tngdev.todoapp.ui.completes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tngdev.todoapp.R

class CompleteFragment : Fragment() {

    private lateinit var completeViewModel: CompleteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        completeViewModel =
                ViewModelProviders.of(this).get(CompleteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_complete, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        completeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}