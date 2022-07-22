package com.example.findtutor.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val isTutor:Boolean,
    val photo: ByteArray,
    val name:String,
    val surname:String,
    val email:String,
    val phone:String,
    val password:String,
    val linkVK:String,
    val id_fk_subject:Int?,
    val experience:Double?,
    val about_me:String?,
    val Latitude:Double?,
    val Longitude:Double?
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