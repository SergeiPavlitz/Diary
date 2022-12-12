package com.pavlitz.diary.entryCreation

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.pavlitz.diary.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class EntryCreationFragment: Fragment(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateText: TextView = view.findViewById(R.id.entry_creation_date)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val date = LocalDateTime.now().format(formatter)
        dateText.text = date
    }
}