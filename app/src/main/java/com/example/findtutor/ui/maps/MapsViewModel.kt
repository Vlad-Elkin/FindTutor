package com.example.findtutor.ui.maps

import android.app.Application
import android.util.Range
import androidx.lifecycle.*
import com.example.findtutor.data.entities.User

import com.example.findtutor.data.repository.TutorRepository
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsViewModel(application: Application):AndroidViewModel(application) {

    private var repository = TutorRepository(getApplication<Application>().applicationContext)

    var subjectFilter = MutableLiveData<Int>()
    var expFilter = MutableLiveData<Range<Double>>()

    private val allUsers = repository.userList

    private val tutors: LiveData<List<User>>
        get() {
            val filteredListBySubject = Transformations.switchMap(subjectFilter){ subject_id ->
                val tutorsByFilters = when (subject_id) {
                    null -> allUsers
                    else -> {
                        Transformations.switchMap(allUsers){ list->
                            val filteredTutors = MutableLiveData<List<User>>()
                            val filteredList = list.filter { tutor ->
                                tutor.id_fk_subject == subject_id
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
                        val filteredTutors = MutableLiveData<List<User>>()
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

    private var selected:MutableLiveData<User> = MutableLiveData()

    fun selectMarker(marker: Marker): MutableLiveData<User> {
        allUsers.value?.forEach {
            if(it.toMarkerOptions().title==marker.title) selected.postValue(it)
        }
        return selected
    }
}