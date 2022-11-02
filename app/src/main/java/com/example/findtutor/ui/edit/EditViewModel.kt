package com.example.findtutor.ui.edit

import android.graphics.*
import android.widget.ImageView
import androidx.databinding.*
import androidx.lifecycle.ViewModel
import com.example.findtutor.data.entities.User

class EditViewModel : ViewModel() {
    lateinit var user: User

    var isTutor:Boolean
        get() = user.isTutor
        set(value) {user.isTutor = value}

    var photo:ByteArray
        get() = user.photo
        set(value) { user.photo = value }

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
        get() = user.id_fk_subject.minus(1)
        set(value) {
            if (value != null) { user.id_fk_subject = value.plus(1) }
        }

    var experience: String?
        get() = user.experience.toString()
        set(value) {
            if (value != null) { user.experience = value.toDouble() }
        }

    var about_me: String?
        get() = user.about_me
        set(value) {
            if (value != null) { user.about_me = value }
        }

    companion object{
        @JvmStatic
        @BindingAdapter("app:srcCompat")
        fun setImage(imageView: ImageView,image:ByteArray){
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.size))
        }
    }

}