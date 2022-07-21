package com.example.findtutor.data.entities

import android.graphics.BitmapFactory
import androidx.core.graphics.scale
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class TutorMarker(_tutor:User,_subject:Subject) {
    var tutor:User? = null
    var subject: Subject? = null
    var marker:MarkerOptions = MarkerOptions()
    init {
        tutor = _tutor
        subject = _subject
        with(tutor!!){
            val image = BitmapFactory.decodeByteArray(photo,0,photo.size)
                .scale(128+64,128+64,true)
            marker.title("$id")
            marker.icon(
                BitmapDescriptorFactory.fromBitmap(image)
            )
            marker.position(LatLng(Latitude!!,Longitude!!))
        }
    }
}