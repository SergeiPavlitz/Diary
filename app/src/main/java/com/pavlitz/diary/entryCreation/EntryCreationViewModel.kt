package com.pavlitz.diary.entryCreation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlitz.diary.database.DiaryDataBaseDao
import com.pavlitz.diary.database.DiaryEntry
import kotlinx.coroutines.launch
import java.util.*

class EntryCreationViewModel(datasource: DiaryDataBaseDao) : ViewModel() {

    private val database = datasource
    private val _date = MutableLiveData<Date>()
    private val _topic = MutableLiveData<String>()
    private val _body = MutableLiveData<String>()

    fun isBlanc(): Boolean {
        if (getBody().isBlank() && getTopic().isBlank()) {
            return true
        }
        return false
    }

    fun getDate(): Long {
        return _date.value?.time ?: 0
    }

    fun getTopic(): String {
        return _topic.value ?: ""
    }

    fun getBody(): String {
        return _topic.value ?: ""
    }

    fun setTopic(topic: String) {
        _topic.value = topic
    }

    fun setBody(body: String) {
        _body.value = body
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("EntryCreationViewModel", "EntryCreationViewModel cleared")
    }

    fun saveEntry() {
        viewModelScope.launch {
            val d = DiaryEntry()
            d.creationDate = getDate()
            d.topic = getTopic()
            d.body = getBody()
            database.insert(d)
        }
    }

    init {
        Log.i("EntryCreationViewModel", "EntryCreationViewModel created!")
        _date.value = Calendar.getInstance().time
        _topic.value = ""
        _body.value = ""
    }
}