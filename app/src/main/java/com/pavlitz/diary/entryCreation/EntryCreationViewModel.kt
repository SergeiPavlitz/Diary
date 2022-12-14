package com.pavlitz.diary.entryCreation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class EntryCreationViewModel : ViewModel() {

    private val _date = MutableLiveData<Date>()
    private val _topic = MutableLiveData<String>()
    private val _body = MutableLiveData<String>()


    val date: LiveData<Date>
        get() = _date
    val topic: LiveData<String>
        get() = _topic
    val body: LiveData<String>
        get() = _body

    fun isBlanc(): Boolean {
        if (body.value?.isBlank() == true && topic.value?.isBlank() == true) {
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

    init {
        Log.i("EntryCreationViewModel", "EntryCreationViewModel created!")
        _date.value = Calendar.getInstance().time
        _topic.value = ""
        _body.value = ""
    }
}