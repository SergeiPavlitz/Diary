package com.pavlitz.diary.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavlitz.diary.database.auth.AuthDataBaseDao

class LoginViewModelFactory(private val dataSource: AuthDataBaseDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}