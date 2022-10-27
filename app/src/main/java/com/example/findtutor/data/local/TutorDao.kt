package com.example.findtutor.data.local

import androidx.lifecycle.LiveData
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

    @Query("SELECT id,isTutor,photo,"+
            "name, surname," +
            "email, phone, password, linkVK," +
            "id_subject,subject_name, subject_possessive," +
            "experience, about_me," +
            "Latitude,Longitude " +
            "FROM user_table as User," +
            "subject_table as Subject " +
            "WHERE User.id_fk_subject = Subject.id_subject")
    fun getTutorList():LiveData<List<Tutor>>

}