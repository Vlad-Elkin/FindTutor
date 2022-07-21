package com.example.findtutor.ui.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findtutor.data.local.ExampleData
import com.example.findtutor.data.repository.SubjectRepository
import com.example.findtutor.data.repository.UserRepository
import kotlinx.coroutines.launch

class StartViewModel(application: Application):AndroidViewModel(application) {
    init {
        with(ExampleData(application.applicationContext)){
            users.forEach {
                UserRepository(application.applicationContext).insertData(it)
            }
            subjects.forEach {
                SubjectRepository(application.applicationContext).insertData(it)
            }
        }
    }
}