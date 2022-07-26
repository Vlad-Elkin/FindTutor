package com.example.findtutor.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.findtutor.data.entities.*

@Database(
    entities = [
        User::class,
        Subject::class
    ],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun tutorDao():TutorDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "DATABASE").fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }
}