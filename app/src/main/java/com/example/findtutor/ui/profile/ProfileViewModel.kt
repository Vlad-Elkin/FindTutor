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


class ProfileViewModel: ViewModel() {
    val profile = MutableLiveData<Tutor>()

    init {

    }


    val photo: MutableLiveData<Drawable>
        get() {
            var byteArray = profile.value?.photo
            var bitmap = byteArray?.let { BitmapFactory.decodeByteArray(it,0,it.size) }
                return  MutableLiveData(
                    BitmapDrawable(Resources.getSystem(),bitmap)
                )
            }

    val surname:MutableLiveData<String>
        get() {
            return MutableLiveData(profile.value?.surname ?: "Фамилия")
        }

    val name:MutableLiveData<String>
        get() {
            return MutableLiveData(profile.value?.name ?: "Имя")
        }

    val email:MutableLiveData<String>
        get() {
            return MutableLiveData(profile.value?.email ?: "E-mail")
        }

    val phone:MutableLiveData<String>
        get() {
            return MutableLiveData(profile.value?.phone ?: "Телефон")
        }

    val subject:MutableLiveData<String>
        get() {
            return MutableLiveData("Репетитор по " + (profile.value?.subject_possessive ?: "предмету"))
        }

    val exp:MutableLiveData<String>
        get() {
            val year = with(profile.value?.experience?.toInt()) {
                val tens = this?.rem(100)
                val ones = this?.rem(10)
                when (tens) {
                    in 10..20 -> "лет"
                    else -> {
                        when (ones) {
                            1 -> "год"
                            in 2..4 ->"года"
                            else -> "лет"
                        }
                    }
                }
            }
            return MutableLiveData(
                "Стаж репетиторства:  ${profile.value?.experience?:0} $year")
        }
    val aboutSelf:MutableLiveData<String>
        get() {
            return MutableLiveData("О себе: " + (profile.value?.about_me ?:
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."))
        }
}