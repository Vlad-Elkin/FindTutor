package com.example.findtutor.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findtutor.data.entities.*


class ProfileViewModel: ViewModel() {
    private val userLiveData = MutableLiveData<Tutor>()
    var surname = "Фамилия"
    var name = "Имя"
    var email = "E-mail"
    var phone = "Телефон"
    var subject = "Репетитор по предмету"
    var exp = "0 лет"
    var aboutSelf = "Lorem ipsum dolor sit amet, " +
            "\nconsectetur adipiscing elit," +
            "\nsed do eiusmod tempor incididunt " +
            "\nut labore et dolore magna aliqua."
}