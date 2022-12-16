package com.pavlitz.diary.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlitz.diary.database.DiaryDataBaseDao
import com.pavlitz.diary.database.DiaryEntry
import kotlinx.coroutines.launch

class DiaryHomeViewModel(datasource: DiaryDataBaseDao) : ViewModel() {

    private val database = datasource
    val items = database.getAllForView()

    fun saveEntry(dateMilli: Long, topic: String, body: String) {
        viewModelScope.launch {
            val d = DiaryEntry()
            d.creationDate = dateMilli
            d.topic = topic
            d.body = body
            insert(d)
        }
    }

    private suspend fun insert(d: DiaryEntry) {
        database.insert(d)
    }

    fun clearEntries() {
        viewModelScope.launch {
            database.clear()
        }
    }

}