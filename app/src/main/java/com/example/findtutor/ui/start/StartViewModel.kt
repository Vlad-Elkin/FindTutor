package com.example.findtutor.ui.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.findtutor.data.local.ExampleData
import com.example.findtutor.data.repository.TutorRepository

class StartViewModel(application: Application):AndroidViewModel(application) {
    init {
        val repository = TutorRepository(application.applicationContext)
        with(ExampleData(application.applicationContext)){
            users.forEach {
                repository.insertUser(it)
            }
            subjects.forEach {
                repository.insertSubject(it)
            }
        }
    }
}