package com.example.findtutor.ui.maps

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.findtutor.data.entities.TutorMarker
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.launch

class MapsViewModel(application: Application):AndroidViewModel(application) {
    var subjectFilter1 = MutableLiveData<Int>()
    var expFilter1 = MutableLiveData<Int>()
    private var subjectFilter = 1
    private var expFilter = 0.0 .. 100.0

    var selected: TutorMarker? = null
    var tutorMarkers:MutableLiveData<List<TutorMarker>> = MutableLiveData()

    fun loadMarkers(){
        viewModelScope.launch {

        }

    }

    fun filterBySubject(id: Int) {
        subjectFilter = id+1
    }

    fun filterByExperience(id: Int) {
        when(id){
            0 ->{expFilter = 0.0 .. 0.9}
            1 ->{expFilter = 1.0 .. 2.9}
            2 ->{expFilter = 3.0 .. 4.9}
            3 ->{expFilter = 5.0 .. 9.9}
            4 ->{expFilter = 10.0 .. 100.0}
        }
    }
    fun selectMarker(marker: Marker){
        tutorMarkers.value?.forEach {
            if (it.marker.title==marker.title) selected = it
            with(it.tutor!!){
                Log.d("tutor","$name $surname")
            }
            with(selected?.tutor!!){
                Log.d("selected tutor","$name $surname")
            }
        }
    }
}