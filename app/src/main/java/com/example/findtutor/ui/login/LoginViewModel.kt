package com.example.findtutor.ui.login

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findtutor.data.entities.Tutor
import com.example.findtutor.data.repository.TutorRepository
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlin.math.log

class LoginViewModel(application:Application) : AndroidViewModel(application) {
    val repository = TutorRepository(application.applicationContext)

    fun checkTutor(login:String, password:String): Tutor? {
        var t:Tutor? = null
        viewModelScope.launch {
            if (!login.contains("@")){
                repository.tutorList.value?.forEach {
                    if (it.phone==login && it.password==password){
                        t = it
                    }

                }

            }
            else{
                repository.tutorList.value?.forEach {
                    if (it.email == login && it.password == password ){
                        t = it
                    }
                }

            }
        }
        return t
    }

}