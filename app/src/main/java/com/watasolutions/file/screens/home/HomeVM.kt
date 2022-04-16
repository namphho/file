package com.watasolutions.file.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watasolutions.file.repository.UserRepository
import kotlinx.coroutines.launch

class HomeVM(private val userRepository: UserRepository) : ViewModel() {
    private var _registerSuccessEvent: MutableLiveData<Boolean?> = MutableLiveData()
    val registerSuccessEvent: LiveData<Boolean?>
        get() = _registerSuccessEvent

    fun insertUser(username: String, pass: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            userRepository.insertUser(firstName, lastName, username = username, password = pass)
            _registerSuccessEvent.postValue(true)
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = userRepository.getUsers()
            Log.e("MainVM", users.toString())
        }
    }
}