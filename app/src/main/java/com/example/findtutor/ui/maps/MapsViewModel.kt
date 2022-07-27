package com.example.findtutor.ui.maps

import android.app.Application
import android.util.Log
import android.util.Range
import androidx.core.util.toRange
import androidx.lifecycle.*

import com.example.findtutor.data.repository.TutorRepository
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MapsViewModel(application: Application):AndroidViewModel(application) {
    var repository = TutorRepository(getApplication<Application>().applicationContext)

    var allTutors = repository.tutorList

    val markers = MutableLiveData<List<MarkerOptions>>()

    private var subjectChannel = Channel<Int>()
    private var expChannel = Channel<Range<Double>>()

    init {
        viewModelScope.launch {

            val subjectFlow = subjectChannel.receiveAsFlow()
            val expFlow = expChannel.receiveAsFlow()
            allTutors.collect{ list ->
                markers.value = list.map { it.toMarkerOptions() }
            }
        }
    }

    fun filterBySubject(id: Int) {
        subjectChannel.trySend(id+1)
    }

    fun filterByExperience(id: Int) {
        when(id){
            0 ->{
                expChannel.trySend((0.0..0.9).toRange())
            }
            1 ->{
                expChannel.trySend((1.0..2.9).toRange())
            }
            2 ->{
                expChannel.trySend((3.0..4.9).toRange())
            }
            3 ->{
                expChannel.trySend((5.0..9.9).toRange())
            }
            4 ->{
                expChannel.trySend((10.0..100.0).toRange())
            }
        }
    }
    fun selectMarker(marker: Marker){

    }
}