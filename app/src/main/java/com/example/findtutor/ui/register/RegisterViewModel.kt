package com.example.findtutor.ui.register

import android.app.Application
import android.content.Intent
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.repository.TutorRepository
import com.google.android.gms.maps.model.LatLng

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    val repository = TutorRepository(application.applicationContext)
    fun insertNewUser(isTutor:Boolean,
                      photo:ByteArray, name:String,surname: String,
                      email:String,phone:String,password:String,vk:String,
                      subject:Int,exp:Double,about_me:String,position:LatLng){
        repository.insertUser(User(0,isTutor,
            photo,name,surname,
            email,phone, password,vk,
            subject,exp,about_me,position.latitude,position.longitude))
    }
}