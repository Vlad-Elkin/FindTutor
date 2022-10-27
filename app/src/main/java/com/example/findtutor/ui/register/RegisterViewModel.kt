package com.example.findtutor.ui.register

import android.app.Application
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.repository.TutorRepository
import com.google.android.gms.maps.model.LatLng

class RegisterViewModel(application: Application) : AndroidViewModel(application),LocationListener {
    private val repository = TutorRepository(application.applicationContext)
    private val latitude = MutableLiveData(0.0)
    private val longitude = MutableLiveData(0.0)
    fun insertNewUser(isTutor:Boolean,
                      photo:ByteArray, name:String,surname: String,
                      email:String,phone:String,password:String,vk:String,
                      subject:Int,exp:Double,about_me:String){
        repository.insertUser(User(0,isTutor,
            photo,name,surname,
            email.lowercase().replace(" ",""),phone, password,vk,
            subject,exp,about_me,latitude.value,longitude.value))
    }

    override fun onLocationChanged(loc: Location) {
        latitude.postValue(loc.latitude)
        longitude.postValue(loc.longitude)
    }
}