package com.example.findtutor.ui.profile

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findtutor.data.entities.*


class ProfileViewModel(): ViewModel() {


    var photo: Drawable?=null

    val surname:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val name:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val email:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val phone:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val subject:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val exp:MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val aboutSelf:MutableLiveData<String> by lazy {
        MutableLiveData()
    }
    val actionBtnText:MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun setProfile(profile: Tutor, isTutorProfile: Boolean){
        with(profile.photo){
            val bitmap = BitmapFactory.decodeByteArray(this,0,this.size)
            photo = BitmapDrawable(Resources.getSystem(),bitmap)
        }
        surname.value = profile.surname
        surname.value = profile.surname
        name.value = profile.name
        email.value = profile.email
        phone.value = profile.phone
        if (profile.isTutor){
            subject.value = profile.subject_possessive
            with(profile.experience.toInt()){
                val tens = this%100
                val ones = this%10
                when(tens){
                    in 10..20 -> exp.value = "$this лет"
                    else -> {
                        when(ones){
                            1 -> exp.value = "$this год"
                            in 2..4 -> exp.value = "$this года"
                            else -> exp.value = "$this лет"
                        }
                    }
                }
            }
            aboutSelf.value = profile.about_me
        }
        actionBtnText.value = if (isTutorProfile) "Позвонить" else "Редактировать"
    }
}