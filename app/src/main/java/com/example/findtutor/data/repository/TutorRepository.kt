package com.example.findtutor.data.repository

import android.content.Context
import com.example.findtutor.data.entities.*
import com.example.findtutor.data.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TutorRepository(context: Context) {
    private var dao = AppDatabase.getInstance(context).tutorDao()

    var userList = dao.getUserList()

    var subjectList = dao.getSubjectList()

    fun insertUser(user: User){
        CoroutineScope(Dispatchers.IO).launch{
            dao.insert(user)
        }
    }

    fun insertSubject(subject: Subject){
        CoroutineScope(Dispatchers.IO).launch{
            dao.insert(subject)
        }
    }

}