package com.example.findtutor.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.findtutor.data.entities.Subject
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.local.AppDatabase
import com.example.findtutor.data.local.SubjectDao
import com.example.findtutor.data.local.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SubjectRepository(context: Context) {
    private var dao = AppDatabase.getInstance(context).subjectDao()

    fun getDataById(id:Int): Flow<Subject> {
        return dao.getByID(id)
    }
    fun insertData(subjectDetail: Subject){
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(subjectDetail)
        }
    }
}