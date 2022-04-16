package com.watasolutions.file.repository

import com.watasolutions.file.database.AppDatabase
import com.watasolutions.file.database.User

class UserRepository(val appDatabase: AppDatabase) {

    suspend fun insertUser(
        firstName: String,
        lastName: String,
        username: String,
        password: String
    ) {
        return appDatabase.userDao().insertAll(
            User(
                firstName = firstName,
                lastName = lastName,
                username = username,
                password = password
            )
        )
    }

    suspend fun getUsers(): List<User> {
        return appDatabase.userDao().getAll()
    }
}