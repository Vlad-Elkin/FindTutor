package com.example.findtutor.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.findtutor.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TutorListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subject: Subject)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)
}