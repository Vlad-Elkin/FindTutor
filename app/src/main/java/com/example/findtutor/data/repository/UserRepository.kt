package com.example.findtutor.data.repository

import android.content.Context
import com.example.findtutor.data.entities.Subject
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.local.AppDatabase
import com.example.findtutor.data.local.UserDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class UserRepository(context: Context) {
    private var dao = AppDatabase.getInstance(context).userDao()
    var users = dao.getAll()

    fun insertData(userDetails: User){
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(userDetails)
        }
    }
}