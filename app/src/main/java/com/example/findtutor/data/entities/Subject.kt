package com.example.findtutor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_subject")
    var id: Int,
    @ColumnInfo(name = "subject_name")
    val name:String,
    @ColumnInfo(name = "subject_possessive")
    val possessive:String
)