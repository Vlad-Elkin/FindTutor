package com.example.findtutor.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.findtutor.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:User)

    @Query("SELECT * FROM user_table")
    fun getAll(): Flow<List<User>>
}