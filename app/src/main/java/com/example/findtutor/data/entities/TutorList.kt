package com.example.findtutor.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.coroutines.flow.Flow

data class TutorList(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "id",
        entityColumn = "subject_id")
    val tutors: List<User>
)