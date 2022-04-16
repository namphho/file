package com.watasolutions.file

import android.app.Application
import com.watasolutions.file.database.AppDatabase
import com.watasolutions.file.repository.UserRepository

class AppData : Application() {
    lateinit var database : AppDatabase
    val userRepository by lazy { UserRepository(database) }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
    }
}