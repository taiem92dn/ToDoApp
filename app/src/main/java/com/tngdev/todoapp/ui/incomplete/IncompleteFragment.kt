package com.tngdev.todoapp.ui.incomplete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tngdev.todoapp.R

class IncompleteFragment : Fragment() {

    private lateinit var inCompleteViewModel: InCompleteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        inCompleteViewModel =
                ViewModelProviders.of(this).get(InCompleteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_incomplete, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        inCompleteViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}