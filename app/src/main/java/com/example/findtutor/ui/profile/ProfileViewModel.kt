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
import androidx.lifecycle.viewModelScope
import com.example.findtutor.data.entities.*
import com.example.findtutor.data.repository.TutorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileViewModel(): ViewModel() {
    var isTutorProfile = false
    val photo = MutableLiveData<Drawable>()
    val surname = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val vk = MutableLiveData<String>()
    val subject = MutableLiveData<String>()
    val exp= MutableLiveData<String>()
    val aboutSelf = MutableLiveData<String>()
    val actionBtnText = MutableLiveData<String>()

    fun setProfile(profile:User,subjectList: List<Subject>,_isTutorProfile: Boolean = false){
        isTutorProfile = _isTutorProfile
        with(profile.photo){
            val bitmap = BitmapFactory.decodeByteArray(this,0,this.size)
            photo.postValue(BitmapDrawable(Resources.getSystem(),bitmap))
        }
        surname.postValue(profile.surname)
        name.postValue(profile.name)
        email.postValue(profile.email)
        phone.postValue(profile.phone)
        vk.postValue(profile.linkVK)
        if (_isTutorProfile){
            subject.postValue(subjectList.filter { it.id == profile.id_fk_subject }[0].possessive)
            with(profile.experience.toInt()){
                val tens = this%100
                val ones = this%10
                when(tens){
                    in 10..20 -> exp.postValue("$this лет")
                    else -> {
                        when(ones){
                            1 -> exp.postValue("$this год")
                            in 2..4 -> exp.postValue("$this года")
                            else -> exp.postValue("$this лет")
                        }
                    }
                }
            }
            aboutSelf.postValue(profile.about_me)
            actionBtnText.postValue("Позвонить")
        }
        else{
            actionBtnText.postValue("Редактировать")
        }
    }
}