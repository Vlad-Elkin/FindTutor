package com.example.findtutor.ui.start

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import com.example.findtutor.data.entities.Tutor
import com.example.findtutor.data.local.ExampleData
import com.example.findtutor.data.repository.TutorRepository
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList

class StartViewModel(application: Application):AndroidViewModel(application) {
    val repository = TutorRepository(application.applicationContext)
    init {
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