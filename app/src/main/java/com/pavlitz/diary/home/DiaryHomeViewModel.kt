package com.pavlitz.diary.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlitz.diary.database.DiaryDataBaseDao
import kotlinx.coroutines.launch

class DiaryHomeViewModel(datasource: DiaryDataBaseDao) : ViewModel() {

    private val database = datasource
    val items = database.getAllForView()

    fun clearEntries() {
        viewModelScope.launch {
            database.deleteAll()
        }
    }

}