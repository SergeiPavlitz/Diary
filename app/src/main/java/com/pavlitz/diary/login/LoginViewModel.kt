package com.pavlitz.diary.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlitz.diary.database.auth.AuthDataBaseDao
import com.pavlitz.diary.database.auth.AuthEntity
import kotlinx.coroutines.launch

class LoginViewModel(val dataSource: AuthDataBaseDao) : ViewModel() {
    private val dataBase = dataSource
    val authorization = dataBase.getAuth()
    private var pin : Int = -1

    private fun insert(pin: Int) {
        viewModelScope.launch {
            val a = AuthEntity(0, pin)
            dataBase.insert(a)
            resetPin()
        }
    }

    fun setPin(pin: Int) {
        this.pin = pin
    }

    fun confirm(pinConfirm: Int):Boolean {
        if (pin == pinConfirm){
            insert(pin)
            return true
        }
       return false
    }

    fun authorize(entered: Int):Boolean{
        if (authorization.value != null) {
            return authorization.value!!.pin == entered
        }
        return false
    }

    private fun resetPin(){
        pin = -1
    }

}