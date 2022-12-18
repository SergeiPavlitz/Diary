package com.pavlitz.diary.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlitz.diary.database.auth.AuthDataBaseDao
import kotlinx.coroutines.launch

class LoginViewModel(val dataSource: AuthDataBaseDao) : ViewModel(){
    private val dataBase = dataSource
    private val authorization = dataBase.getAuth()

    fun insert(pin: Int) {
        viewModelScope.launch {

        }
    }

    fun isFirstTime():Boolean{
        return authorization.value == null
    }
}