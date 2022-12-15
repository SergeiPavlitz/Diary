package com.pavlitz.diary.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pavlitz.diary.database.DiaryDataBaseDao
import com.pavlitz.diary.database.DiaryEntry
import com.pavlitz.diary.database.DiaryItemView

class DiaryHomeViewModel(datasource: DiaryDataBaseDao) : ViewModel() {

    private val database: DiaryDataBaseDao
    private val itemsViewModel: ArrayDeque<DiaryItemView>

    fun saveEntry(dateMilli: Long, topic: String, body: String) {
        val d = DiaryEntry()
        d.creationDate = dateMilli
        d.topic = topic
        d.body = body
        insert(d)
        val dv = database.getLastInsertedByTopic(topic)
        itemsViewModel.addFirst(dv)
    }

    private fun insert(d: DiaryEntry) {
        database.insert(d)
    }

    fun getItemViewList(): ArrayDeque<DiaryItemView> {
        return itemsViewModel
    }

    fun clearEntries() {
        database.clear()
        itemsViewModel.clear()
    }

    init {
        database = datasource
        itemsViewModel = ArrayDeque(database.getAllForView())
        Log.i("DiaryHomeViewModel", "DiaryHomeViewModel created!")
        Log.i("DiaryHomeViewModel", "is empty " + (itemsViewModel.isEmpty()))

    }
}