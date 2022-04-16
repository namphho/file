package com.watasolutions.file.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.watasolutions.file.AppData
import com.watasolutions.file.screens.home.HomeFragment
import com.watasolutions.file.screens.home.HomeVM

class ViewModelFactory(private val appData: AppData) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeVM::class.java)) {
            val repo = appData.userRepository
            return HomeVM(repo) as T
        }
        throw IllegalArgumentException("unknown view model")
    }
}