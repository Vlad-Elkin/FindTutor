package com.example.findtutor.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.repository.TutorRepository
import kotlinx.coroutines.launch

class LoginViewModel(application:Application) : AndroidViewModel(application) {
    val repository = TutorRepository(application.applicationContext)

    fun checkTutor(login:String, password:String): User? {
        var t:User? = null
        viewModelScope.launch {
            if (!login.contains("@")){
                repository.userList.value?.forEach {
                    if (it.phone==login && it.password==password){
                        t = it
                    }
                }
            }
            else{
                repository.userList.value?.forEach {
                    if (it.email == login && it.password == password ){
                        t = it
                    }
                }

            }
        }
        return t
    }

}