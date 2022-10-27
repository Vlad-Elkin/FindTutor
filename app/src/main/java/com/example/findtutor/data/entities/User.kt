package com.example.findtutor.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var isTutor:Boolean,
    var photo: ByteArray,
    var name:String,
    var surname:String,
    var email:String,
    var phone:String,
    var password:String,
    var linkVK:String,
    var id_fk_subject:Int?,
    var experience:Double?,
    var about_me:String?,
    var Latitude:Double?,
    var Longitude:Double?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (isTutor != other.isTutor) return false
        if (!photo.contentEquals(other.photo)) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (email != other.email) return false
        if (phone != other.phone) return false
        if (password != other.password) return false
        if (linkVK != other.linkVK) return false
        if (id_fk_subject != other.id_fk_subject) return false
        if (experience != other.experience) return false
        if (about_me != other.about_me) return false
        if (Latitude != other.Latitude) return false
        if (Longitude != other.Longitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isTutor.hashCode()
        result = 31 * result + photo.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + linkVK.hashCode()
        result = 31 * result + (id_fk_subject ?: 0)
        result = (31 * result + (experience ?: 0.0)).toInt()
        result = 31 * result + (about_me?.hashCode() ?: 0)
        result = 31 * result + (Latitude?.hashCode() ?: 0)
        result = 31 * result + (Longitude?.hashCode() ?: 0)
        return result
    }
}