package com.example.findtutor.ui.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.findtutor.data.local.ExampleData
import com.example.findtutor.data.repository.TutorListRepository

class StartViewModel(application: Application):AndroidViewModel(application) {
    init {
        val repository = TutorListRepository(application.applicationContext)
        with(ExampleData(application.applicationContext)){
            users.forEach {
                repository.insertData(it)
            }
            subjects.forEach {
                repository.insertData(it)
            }
        }
    }
}