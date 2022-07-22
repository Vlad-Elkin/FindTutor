package com.example.findtutor.data.repository

import android.content.Context
import com.example.findtutor.data.entities.Subject
import com.example.findtutor.data.entities.TutorList
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TutorListRepository(context: Context) {
    private var dao = AppDatabase.getInstance(context).tutorListDao()

    fun insertData(obj: User){
        CoroutineScope(Dispatchers.IO).launch{
            dao.insert(obj)
        }
    }
    fun insertData(obj: Subject){
        CoroutineScope(Dispatchers.IO).launch{
            dao.insert(obj)
        }
    }

}