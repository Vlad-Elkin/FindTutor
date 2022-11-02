package com.example.findtutor.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.findtutor.data.entities.*

@Dao
interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT id_subject,subject_name,subject_possessive "+
            "FROM subject_table")
    fun getSubjectList():LiveData<List<Subject>>

    @Query("SELECT id,isTutor,photo,"+
            "name, surname," +
            "email, phone, password, linkVK," +
            "id_fk_subject," +
            "experience, about_me," +
            "Latitude,Longitude " +
            "FROM user_table as User")
    fun getUserList():LiveData<List<User>>



}