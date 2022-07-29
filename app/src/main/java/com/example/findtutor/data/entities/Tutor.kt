package com.example.findtutor.data.entities

import android.graphics.BitmapFactory
import androidx.core.graphics.scale
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

data class Tutor(
    val id:Int,
    val photo: ByteArray,
    val name:String,
    val surname:String,
    val email:String,
    val phone:String,
    val linkVK:String,
    val id_subject:Int,
    val subject_name:String,
    val subject_possessive:String,
    val experience:Double,
    val about_me:String,
    val Latitude:Double,
    val Longitude:Double){

    fun toMarkerOptions():MarkerOptions{
        val image = BitmapFactory.decodeByteArray(photo,0,photo.size)
            .scale(128+64,128+64,true)
        return MarkerOptions()
            .title("Tutor#$id")
            .position(LatLng(Latitude, Longitude))
            .icon(BitmapDescriptorFactory.fromBitmap(image))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tutor

        if (id != other.id) return false
        if (!photo.contentEquals(other.photo)) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (email != other.email) return false
        if (phone != other.phone) return false
        if (linkVK != other.linkVK) return false
        if (id_subject != other.id_subject) return false
        if (subject_name != other.subject_name) return false
        if (subject_possessive != other.subject_possessive) return false
        if (experience != other.experience) return false
        if (about_me != other.about_me) return false
        if (Latitude != other.Latitude) return false
        if (Longitude != other.Longitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + photo.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + linkVK.hashCode()
        result = 31 * result + id_subject
        result = 31 * result + subject_name.hashCode()
        result = 31 * result + subject_possessive.hashCode()
        result = 31 * result + experience.hashCode()
        result = 31 * result + about_me.hashCode()
        result = 31 * result + Latitude.hashCode()
        result = 31 * result + Longitude.hashCode()
        return result
    }

}