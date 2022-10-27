package com.example.findtutor.ui.maps

import android.app.Application
import android.location.Location
import android.location.LocationListener
import android.util.Log
import android.util.Range
import androidx.lifecycle.*
import com.example.findtutor.data.entities.Tutor

import com.example.findtutor.data.repository.TutorRepository
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsViewModel(application: Application):AndroidViewModel(application) {

    private var repository = TutorRepository(getApplication<Application>().applicationContext)

    var subjectFilter = MutableLiveData<Int>()
    var expFilter = MutableLiveData<Range<Double>>()

    private val allTutors = repository.tutorList

    private val tutors: LiveData<List<Tutor>>
        get() {
            val filteredListBySubject = Transformations.switchMap(subjectFilter){ subject_id ->
                val tutorsByFilters = when (subject_id) {
                    null -> allTutors
                    else -> {
                        Transformations.switchMap(allTutors){ list->
                            val filteredTutors = MutableLiveData<List<Tutor>>()
                            val filteredList = list.filter { tutor -> tutor.id_subject== subject_id
                                    && tutor.isTutor }
                            filteredTutors.value = filteredList
                            filteredTutors
                        }
                    }
                }
                tutorsByFilters
            }
            val filteredListByExperience = Transformations.switchMap(expFilter){exp ->
                val tutorsByFilters = when (exp) {
                    null -> filteredListBySubject
                    else -> Transformations.switchMap(filteredListBySubject) { list ->
                        val filteredTutors = MutableLiveData<List<Tutor>>()
                        val filteredList = list.filter { tutor -> exp.contains(tutor.experience) }
                        filteredTutors.value = filteredList
                        filteredTutors
                    }
                }
                tutorsByFilters
            }
            return filteredListByExperience
        }

    val markers: LiveData<List<MarkerOptions>>
        get() {
            return tutors.map { tutorList -> tutorList.map { it.toMarkerOptions() } }
        }

    private var selected:MutableLiveData<Tutor> = MutableLiveData()

    fun selectMarker(marker: Marker): MutableLiveData<Tutor> {
        allTutors.value?.forEach {
            if(it.toMarkerOptions().title==marker.title) selected.postValue(it)
        }
        return selected
    }

}