package com.pavlitz.diary.entryEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavlitz.diary.database.diary.DiaryDataBaseDao

class EntryEditViewModelFactory(private val key: Long, private val dataSource: DiaryDataBaseDao) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EntryEditViewModel::class.java)) {
            return EntryEditViewModel(key, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}