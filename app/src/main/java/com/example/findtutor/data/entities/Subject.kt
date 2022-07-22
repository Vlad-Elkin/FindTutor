package com.example.findtutor.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "subject_table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    var id_subject: Int,
    val name:String,
    val possessive:String
)