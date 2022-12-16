package com.pavlitz.diary.entryCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavlitz.diary.database.DiaryDataBaseDao

class EntryCreationViewModelFactory(private val dataSource: DiaryDataBaseDao) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryCreationViewModel::class.java)) {
            return EntryCreationViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}