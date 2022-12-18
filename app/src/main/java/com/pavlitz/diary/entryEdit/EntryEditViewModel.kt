package com.pavlitz.diary.entryEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlitz.diary.database.DiaryDataBaseDao
import kotlinx.coroutines.launch

class EntryEditViewModel(private val key: Long = 0L, datasource: DiaryDataBaseDao) : ViewModel() {

    private val database = datasource
    val entryLiveData = database.get(key)

    fun updateEntry(newBody: String, newMood:String, newTopic: String) {
        viewModelScope.launch {
            val entry = entryLiveData.value
            if (entry != null) {
                if (entry.body != newBody || entry.topic != newTopic) {
                    entry.body = newBody
                    entry.topic = newTopic
                    entry.mood = newMood
                    database.update(entry)
                }
            }
        }
    }

    fun deleteEntry() {
        viewModelScope.launch {
            database.delete(key)
        }
    }

}