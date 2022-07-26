package com.example.findtutor.ui.maps

import android.app.Application
import android.util.Log
import androidx.lifecycle.*

import com.example.findtutor.data.repository.TutorRepository
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MapsViewModel(application: Application):AndroidViewModel(application) {
    var repository = TutorRepository(getApplication<Application>().applicationContext)

    var subjectChannel = Channel<Int>()
    var expChannel = Channel<ClosedFloatingPointRange<Double>>()

    private val tutorList = subjectChannel.receiveAsFlow().flatMapLatest {
        repository.getTutorList(it)
    }.zip(expChannel.receiveAsFlow()){
        list, range ->  list.filter { range.contains(it.experience!!)}
    }.asLiveData()

    val markerList = tutorList.map { list ->
        list.map { it.toMarkerOptions() }
    }

    fun filterBySubject(id: Int) {
        subjectChannel.trySend(id+1)
    }

    fun filterByExperience(id: Int) {
        when(id){
            0 ->{expChannel.trySend(0.0 .. 0.9)}
            1 ->{expChannel.trySend(1.0 .. 2.9)}
            2 ->{expChannel.trySend(3.0 .. 4.9)}
            3 ->{expChannel.trySend(5.0 .. 9.9)}
            4 ->{expChannel.trySend(10.0 .. 100.0)}
        }
    }
    fun selectMarker(marker: Marker){

    }
}