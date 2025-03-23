package com.example.studentmanager.data.dao

import androidx.room.*
import com.example.studentmanager.data.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM records WHERE studentId = :studentId ORDER BY timestamp DESC")
    fun getRecordsByStudentId(studentId: String): Flow<List<Record>>

    @Insert
    suspend fun insertRecord(record: Record)

    @Delete
    suspend fun deleteRecord(record: Record)

    @Query("DELETE FROM records WHERE studentId = :studentId")
    suspend fun deleteRecordsByStudentId(studentId: String)
} 