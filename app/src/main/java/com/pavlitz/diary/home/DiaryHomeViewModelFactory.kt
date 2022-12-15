package com.pavlitz.diary.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavlitz.diary.database.DiaryDataBaseDao

class DiaryHomeViewModelFactory(private val dataSource: DiaryDataBaseDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiaryHomeViewModel::class.java)) {
            return DiaryHomeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}