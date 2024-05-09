package com.kunal.learnandroid.fragments.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kunal.learnandroid.R

class SharedDataFirstFragment : Fragment() {

    private val viewModel: SharedDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_shared_data_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstButton = view.findViewById<Button>(R.id.firstButton)

        viewModel.backgroundColor.observe(viewLifecycleOwner, Observer { color ->
            firstButton.setBackgroundColor(Color.parseColor(color))
        })
    }

}