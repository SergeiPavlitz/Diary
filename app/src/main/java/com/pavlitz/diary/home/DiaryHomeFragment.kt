package com.pavlitz.diary.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.pavlitz.diary.R

class DiaryHomeFragment: Fragment(R.layout.diary_fragment_layout){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab: View = view.findViewById(R.id.add_new_entry_button)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here is a snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }
}