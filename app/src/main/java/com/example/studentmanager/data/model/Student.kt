package com.example.studentmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey
    val studentId: String,
    val name: String,
    val projectName: String,
    val className: String,
    val department: String
) 