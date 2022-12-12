package com.pavlitz.diary.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pavlitz.diary.R
import com.pavlitz.diary.entryCreation.EntryCreationFragment

class DiaryHomeFragment: Fragment(R.layout.diary_fragment_layout){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab: View = view.findViewById(R.id.add_new_entry_button)
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Here is a snackbar", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()
            // TODO: add navigation
            val creationFragment = EntryCreationFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(this.id, creationFragment)
                .addToBackStack("ok")
                .commit()
        }
    }
}