package com.example.findtutor.ui.edit

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findtutor.data.entities.User
import java.io.ByteArrayOutputStream

class EditViewModel : ViewModel() {
    lateinit var user: User

    var isTutor:Boolean
        get() = user.isTutor
        set(value) {user.isTutor = value}

    var photo:Bitmap
        get() = user.photo.let {
            BitmapFactory.decodeByteArray(user.photo,0,user.photo.size)
        }
        set(value) {
            val stream =  ByteArrayOutputStream()
            value.compress(Bitmap.CompressFormat.JPEG,100,stream)
            user.photo = stream.toByteArray()
        }

    var name:String
        get() = user.name
        set(value) {user.name = value}

    var surname:String
        get() = user.surname
        set(value) {user.surname = value}

    var email:String
        get() = user.email
        set(value) {user.email = value}

    var phone:String
        get() = user.phone
        set(value) {user.phone = value}

    var password:String
        get() = user.password
        set(value) {user.password = value}

    var linkVK:String
        get() = user.linkVK
        set(value) {user.linkVK = value}

    var id_subject: Int?
        get() = user.id_fk_subject?.minus(1)
        set(value) {
            user.id_fk_subject = value?.plus(1)
        }

    var experience: Double?
        get() = user.experience
        set(value) {user.experience = value}

    var about_me:String?
        get() = user.about_me
        set(value) {user.about_me = value}


}