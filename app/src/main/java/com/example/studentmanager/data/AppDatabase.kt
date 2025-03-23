package com.example.studentmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studentmanager.data.dao.StudentDao
import com.example.studentmanager.data.dao.RecordDao
import com.example.studentmanager.data.model.Student
import com.example.studentmanager.data.model.Record
import com.example.studentmanager.data.util.Converters

@Database(
    entities = [Student::class, Record::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun recordDao(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "student_manager_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 