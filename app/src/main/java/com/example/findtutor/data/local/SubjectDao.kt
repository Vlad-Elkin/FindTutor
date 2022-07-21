package com.example.findtutor.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.findtutor.data.entities.Subject
import com.example.findtutor.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subject:Subject)

    @Query("SELECT * FROM subject_table WHERE id=:id")
    fun getByID(id:Int):Flow<Subject>

    @Query("SELECT * FROM subject_table")
    fun getAll(): Flow<List<Subject>>
}