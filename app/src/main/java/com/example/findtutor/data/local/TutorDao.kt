package com.example.findtutor.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.findtutor.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM subject_table WHERE id_subject=:id")
    fun getSubjectByID(id:Int):Subject

    @Query("SELECT id,photo,"+
            "name, surname," +
            "email, phone, linkVK," +
            "subject_name, subject_possessive," +
            "experience, about_me," +
            "Latitude,Longitude " +
            "FROM user_table," +
            "subject_table  " +
            "WHERE id_subject=:subjectID")
    fun getTutorListBySubjectID(subjectID: Int):Flow<List<Tutor>>
}